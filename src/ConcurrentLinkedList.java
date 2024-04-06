import java.util.concurrent.atomic.AtomicMarkableReference;

public class ConcurrentLinkedList {
    private final Node head;

    public ConcurrentLinkedList() {
        head = new Node(Integer.MIN_VALUE);
        head.next = new AtomicMarkableReference<>(new Node(Integer.MAX_VALUE), false);
    }

    private Window find(Node startNode, int key) {
        Node predecessor, current, successor;
        boolean[] isMarked = {false};
        retry:
        while (true) {
            predecessor = startNode;
            current = predecessor.next.getReference();
            while (true) {
                successor = current.next.get(isMarked);
                while (isMarked[0]) {
                    boolean successfulSnip = predecessor.next.compareAndSet(current, successor, false, false);
                    if (!successfulSnip) continue retry;
                    current = successor;
                    successor = current.next.get(isMarked);
                }
                if (current.key >= key) return new Window(predecessor, current);

                predecessor = current;
                current = successor;
            }
        }
    }

    public boolean add(int key) {
        while (true) {
            Window window = find(head, key);
            Node pred = window.pred, curr = window.curr;

            if (curr.key == key) {
                return false;
            } else {
                Node newNode = new Node(key);
                newNode.next = new AtomicMarkableReference<>(curr, false);
                if (pred.next.compareAndSet(curr, newNode, false, false)) {
                    return true;
                }
            }
        }
    }

    public boolean remove(int key) {
        boolean successfullyMarked;
        while (true) {
            Window window = find(head, key);
            Node pred = window.pred, curr = window.curr;

            if (curr.key != key) {
                return false;
            } else {
                Node succ = curr.next.getReference();
                successfullyMarked = curr.next.compareAndSet(succ, succ, false, true);
                if (!successfullyMarked) continue;

                pred.next.compareAndSet(curr, succ, false, false);
                return true;
            }
        }
    }

    public boolean contains(int key) {
        boolean[] isMarked = {false};
        Node current = head;

        while (current.key < key) {
            current = current.next.getReference();
            current.next.get(isMarked);
        }
        return (current.key == key && !isMarked[0]);
    }

    private static class Node {
        final int key;
        AtomicMarkableReference<Node> next;

        Node(int key) {
            this.key = key;
            this.next = new AtomicMarkableReference<>(null, false);
        }
    }

    private static class Window {
        public final Node pred, curr;

        Window(Node myPred, Node myCurr) {
            this.pred = myPred;
            this.curr = myCurr;
        }
    }
}
