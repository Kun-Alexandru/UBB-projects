import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final Lock mutex = new ReentrantLock();
    private final Condition readyToSendProduct = mutex.newCondition();
    private final Condition readyToReceiveProduct = mutex.newCondition();

    public ProducerConsumer() {
    }

    public Integer get() throws InterruptedException {
        mutex.lock();
        try {
            while (queue.isEmpty()) {
                readyToReceiveProduct.await();
            }

            Integer value = queue.poll();
            System.out.printf("%s extracted value %d from the queue, number elements in q = %d\n", Thread.currentThread().getName(), value, queue.size());
            return value;
        } finally {
            mutex.unlock();
        }
    }

    public void add(int val) throws InterruptedException {
        mutex.lock();
        try {
            queue.add(val);
            System.out.printf("%s added value %d to the queue, number elements in q = %d\n", Thread.currentThread().getName(), val, queue.size());
            readyToReceiveProduct.signal();
        } finally {
            mutex.unlock();
        }
    }
}
