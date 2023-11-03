import java.util.List;

public class Producer extends Thread {

    public ProducerConsumer producerConsumer;

    public List<Integer> vector1, vector2;

    public Producer(ProducerConsumer producerConsumer, List<Integer> vector1, List<Integer> vector2) {
        super("P:");
        this.producerConsumer = producerConsumer;
        this.vector1 = vector1;
        this.vector2 = vector2;
    }

    @Override
    public void run() {
        for (int i = 0; i < vector1.size(); i++) {
            Integer val1 = vector1.get(i);
            Integer val2 = vector2.get(i);
            System.out.printf("P: Send %d * %d = %d\n", val1, val2, val1 * val2);
            producerConsumer.add(val1 * val2);
        }
    }

}