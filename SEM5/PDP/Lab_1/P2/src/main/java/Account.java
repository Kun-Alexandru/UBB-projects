import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {
    private static int count = 0;
    private int id;
    private int amount;
    private Lock lock;
    private List<Log> logs;

    private String name;

    private String generateRandomName() {
        StringBuilder name = new StringBuilder();
        Random rand = new Random();

        // Generate a random alphanumeric string of 10 characters
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < 10; i++) {
            name.append(characters.charAt(rand.nextInt(characters.length())));
        }

        return name.toString();
    }

    public Account() {
        id = count;
        count += 1;
        amount = 10000;
        lock = new ReentrantLock();
        logs = new ArrayList<>();
        name = generateRandomName() + "_" + (id+1);
    }

    public void addTransaction(Account to, int value) {
        logs.add(new Log(to, value));
    }

    public void transfer(Account from, int value) {
        Account small;
        Account big;
        if (this.id < from.id) {
            small = this;
            big = from;
        } else {
            small = from;
            big = this;
        }

        small.lock.lock();
        big.lock.lock();

        try {
            if (from.amount >= value) {
                this.addTransaction(from, -value);
                from.addTransaction(this, value);
                from.amount -= value;
                amount += value;
            }
        } finally {
            big.lock.unlock();
            small.lock.unlock();
        }
    }


    public synchronized boolean verify() {
        int total = amount;
        for (var log : logs) {
            total += log.getValue();
        }
        return total == 10000;
    }
    public int getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void lockAccount() {
        lock.lock();
    }

    public void unlockAccount() {
        lock.unlock();
    }

    @Override
    public String toString() {
        return name;
    }
}
