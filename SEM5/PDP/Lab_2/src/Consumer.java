public class Consumer extends Thread {

    public Integer sum = 0;
    public ProducerConsumer producerConsumer;

    public Integer vectorSize;

    public Consumer(ProducerConsumer producerConsumer, Integer vectorSize) {
        super("C:");
        this.producerConsumer = producerConsumer;
        this.vectorSize = vectorSize;
    }

    @Override
    public void run() {
        for (int i = 0; i < vectorSize; i++) {
            try {
                int val = producerConsumer.get();
                sum += val;
                System.out.printf("C: current sum is %d\n", sum);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}