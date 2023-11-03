import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {
    private final Queue<Integer> queue = new LinkedList<>();
    private final Lock mutex = new ReentrantLock();
    private final Condition getProductCond = mutex.newCondition();

    public ProducerConsumer() {
    }

    public Integer get() throws InterruptedException {
        mutex.lock();
        if (queue.isEmpty()) {
            getProductCond.await();
        }
        Integer value = queue.poll();
        System.out.printf("%s extracted value %d, number elements in q = %d\n", Thread.currentThread().getName(), value, queue.size());
        mutex.unlock();
        return value;
    }

    public void add(int val) {
        mutex.lock();
        queue.add(val);
        System.out.printf("%s added value %d, number elements in q = %d\n", Thread.currentThread().getName(), val, queue.size());
        getProductCond.signal();
        mutex.unlock();
    }
}
