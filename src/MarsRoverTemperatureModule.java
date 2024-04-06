import java.util.Collections;
import java.util.PriorityQueue;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class MarsRoverTemperatureModule {

    private static final int NUMBER_OF_SENSORS = 8;
    private static final int SIMULATION_HOURS = 5;
    private static final int READINGS_PER_HOUR = 60;
    private static final ReentrantLock lock = new ReentrantLock();
    private static PriorityQueue<Integer> minFive;
    private static PriorityQueue<Integer> maxFive;
    private static int[] temperaturesEveryMinute;

    public static void main(String[] args) throws InterruptedException {
        for (int hour = 0; hour < SIMULATION_HOURS; hour++) {
            minFive = new PriorityQueue<>();
            maxFive = new PriorityQueue<>(Collections.reverseOrder());
            temperaturesEveryMinute = new int[60]; // Reset for each hour
            CountDownLatch lock = new CountDownLatch(NUMBER_OF_SENSORS * READINGS_PER_HOUR);

            for (int i = 0; i < NUMBER_OF_SENSORS; i++) {
                new Thread(new TemperatureSensor(i, lock)).start();
            }

            lock.await();
            compileReport();
            System.out.println("End of Hour " + (hour + 1) + " Report");
            System.out.println("\n=====================================");
        }
    }

    private static void compileReport() {
        int largestDiff = 0;
        int startMinuteOfLargestDiff = 0;
        for (int i = 10; i < temperaturesEveryMinute.length; i++) {
            int diff = Math.abs(temperaturesEveryMinute[i] - temperaturesEveryMinute[i - 10]);
            if (diff > largestDiff) {
                largestDiff = diff;
                startMinuteOfLargestDiff = i - 9;
            }
        }

        System.out.println("\nTop 5 Highest Temperatures: " + maxFive);
        System.out.println("Top 5 Lowest Temperatures: " + minFive);
        System.out.println("Largest Temperature Difference Observed During Minutes: " +
                startMinuteOfLargestDiff + " to " + (startMinuteOfLargestDiff + 9));
    }

    static class TemperatureSensor implements Runnable {
        private final Random rand = new Random();
        private final CountDownLatch latch;

        public TemperatureSensor(int sensorId, CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            for (int i = 0; i < READINGS_PER_HOUR; i++) {
                int temperature = rand.nextInt(171) - 100; // Generates a number from -100 to 70

                lock.lock();
                try {
                    if (minFive.size() < 5) minFive.offer(temperature);
                    else {
                        if (temperature > minFive.peek()) {
                            minFive.poll();
                            minFive.offer(temperature);
                        }
                    }

                    if (maxFive.size() < 5) maxFive.offer(temperature);
                    else {
                        if (temperature < maxFive.peek()) {
                            maxFive.poll();
                            maxFive.offer(temperature);
                        }
                    }

                    temperaturesEveryMinute[i % 60] = temperature;
                } finally {
                    lock.unlock();
                }
                latch.countDown();
            }
        }
    }
}
