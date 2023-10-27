import java.util.List;

class Checker extends Thread {
    private int accountCount;
    private List<Account> accounts;

    public Checker(int accountCount, List<Account> accounts) {
        this.accountCount = accountCount;
        this.accounts = accounts;
    }

    public boolean verify() {
        for (var acc : accounts) {
            acc.lockAccount();
        }

        boolean allGood = true;
        for (var acc : accounts) {
            boolean isGood = acc.verify();
            allGood = allGood && isGood;
            String status = isGood ? "good" : "not good";
            System.out.println("Checking account: " + acc + " - Status: " + status);
        }

        for (var acc : accounts) {
            acc.unlockAccount();
        }
        return allGood;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Preserve the interrupted status
                return;
            }
            verify();
        }
    }
}
