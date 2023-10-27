import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Worker extends Thread {
    private int accountCount;
    private List<Account> accounts;

    public Worker(int accountCount, List<Account> accounts) {
        this.accountCount = accountCount;
        this.accounts = accounts;
    }

    public void work() {
        for (int i = 0; i < 100; i++) {
            int from = ThreadLocalRandom.current().nextInt(0, accountCount);
            int to = ThreadLocalRandom.current().nextInt(0, accountCount);
            int value = ThreadLocalRandom.current().nextInt(1, 10);
            accounts.get(to).transfer(accounts.get(from), value);
        }
    }

    @Override
    public void run() {
        work();
    }
}