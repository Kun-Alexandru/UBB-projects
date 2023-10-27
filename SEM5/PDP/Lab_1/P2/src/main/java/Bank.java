import java.util.ArrayList;
import java.util.List;

class Bank {
    private List<Account> accounts;
    private List<Thread> threads;
    private Thread checker;
    private int accountCount = 1000;
    private int threadCount = 20;

    public Bank() {
        accounts = new ArrayList<>();
        threads = new ArrayList<>();
        for (int i = 0; i < accountCount; i++) {
            accounts.add(new Account());
        }
        for (int i = 0; i < threadCount; i++) {
            threads.add(new Worker(accountCount, accounts));
        }
        checker = new Checker(accountCount, accounts);
    }

    public void work() {
        threads.forEach(Thread::start);
        checker.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        checker.interrupt();
    }

    public void verify() {
        int total = 0;
        boolean ok = true;
        List<Account> accountsList = new ArrayList<>();
        for (var a : accounts) {
            System.out.println("=======================================================================================");
            System.out.println(a);
            String status = a.verify() ? "good" : "not good";
            if(status.equals("not good")){
                ok = false;
                accountsList.add(a);
            }
            System.out.println(status);
            System.out.println(a.getAmount());
            total += a.getAmount();
            for (var log : a.getLogs()) {
                System.out.println("\t" + a + " -> " + log);
            }
        }
        if (total == accountCount * 10000 && ok) {
            System.out.println("\nAll transactions are correct, no money lost.");
        }
        else {
            System.out.println("\nSome transactions are incorrect, money lost.\n");
            System.out.println("\nAccounts that have inccorent money are:\n");
            for(var a: accountsList)
                System.out.println(a);
        }
    }
}