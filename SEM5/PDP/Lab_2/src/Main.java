import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> vector1 = new ArrayList<>();
        ArrayList<Integer> vector2 = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            vector1.add(random.nextInt(10) + 1);
            vector2.add(random.nextInt(10) + 1);
        }

        ProducerConsumer producerConsumer = new ProducerConsumer();
        Producer producer = new Producer(producerConsumer, vector1, vector2);
        Consumer consumer = new Consumer(producerConsumer, vector1.size());

        producer.start();
        consumer.start();
    }
}
