import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;


public class BirthdayPresentsParty extends Thread
{

    public static final int TOTAL_PRESENTS = 500000;
    public static final int NUM_OF_WORKERS = 4;
    public static ConcurrentLinkedList presentsChain = new ConcurrentLinkedList();
    public static ArrayList<Integer> presentsAdded =  new ArrayList<>();
    public static AtomicInteger cardsWritten = new AtomicInteger(0);
    public static ArrayList<Worker> threads = new ArrayList<>();
    public Stack<Integer> gifts = new Stack<>();
    ReentrantLock lock = new ReentrantLock();




    void runWorkers(BirthdayPresentsParty mainThread) throws InterruptedException
    {


        for (int i = 1; i <= this.NUM_OF_WORKERS; i++)
            threads.add(new Worker(i, mainThread));

        for (int i = 0; i < mainThread.NUM_OF_WORKERS; i++)
            threads.get(i).start();

        for (int i = 0; i < mainThread.NUM_OF_WORKERS; i++)
            threads.get(i).join();

        System.out.println(cardsWritten + " Thank you notes were written!");

    }

    public static void main(String args[]) throws InterruptedException
    {

        BirthdayPresentsParty mainThread = new BirthdayPresentsParty();

        for (int i = 1; i <= mainThread.TOTAL_PRESENTS; i++)
            mainThread.gifts.push(i);

        Collections.shuffle(mainThread.gifts);

        final long startTime = System.currentTimeMillis();

        mainThread.runWorkers(mainThread);

        final long endTime = System.currentTimeMillis();
        final long runTime = endTime - startTime;
        System.out.println("Execution time: " + runTime + " ms");
    }
}
class Worker extends Thread {
    int threadNum;
    BirthdayPresentsParty mainThread;

    Worker(int threadNum, BirthdayPresentsParty mainThread) {
        this.threadNum = threadNum;
        this.mainThread = mainThread;
    }

    @Override
    public void run() {
        while (mainThread.cardsWritten.get() < mainThread.TOTAL_PRESENTS) {
            int taskRand = (int) (Math.random() * 3 + 1);
            switch (taskRand) {
                case 1:
                    handleAddPresent();
                    break;
                case 2:
                    handleWriteThankYou();
                    break;
                case 3:
                    handleCheckPresent();
                    break;
            }
        }
    }

    private void handleAddPresent() {
        System.out.println("Worker #" + threadNum + " is adding a present.");
        mainThread.lock.lock();
        try {
            if (!mainThread.gifts.empty()) {
                int gift = mainThread.gifts.pop();
                mainThread.presentsAdded.add(gift);
                mainThread.presentsChain.add(gift);
                System.out.println("Worker #" + threadNum + " added present #" + gift);
            }
        } finally {
            mainThread.lock.unlock();
        }
    }

    private void handleWriteThankYou() {
        System.out.println("Worker #" + threadNum + " is writing a Thank You note.");
        mainThread.lock.lock();
        try {
            if (!chainIsEmpty()) {
                int randIndex = (int) (Math.random() * mainThread.presentsAdded.size());
                int gift = mainThread.presentsAdded.remove(randIndex);
                mainThread.presentsChain.remove(gift);
                mainThread.cardsWritten.incrementAndGet();
                System.out.println("Worker #" + threadNum + " wrote a Thank You note for present #" + gift);
            }
        } finally {
            mainThread.lock.unlock();
        }
    }

    private void handleCheckPresent() {
        int randGift = (int) (Math.random() * mainThread.TOTAL_PRESENTS + 1);
        System.out.println("Worker #" + threadNum + " is checking for the presence of gift #" + randGift);
        mainThread.presentsChain.contains(randGift);
    }

    private boolean chainIsEmpty() {
        return mainThread.presentsAdded.isEmpty();
    }
}
