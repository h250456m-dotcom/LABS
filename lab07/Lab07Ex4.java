/** Lab 7, Exercise 4 (Intermediate): BankAccount.withdraw() throws InsufficientFundsException. */
public class Lab07Ex4 {

    static class InsufficientFundsException extends Exception {
        private final double shortfall;
        InsufficientFundsException(String message, double shortfall) {
            super(message);
            this.shortfall = shortfall;
        }
        double getShortfall() { return shortfall; }
    }

    static class BankAccount {
        private double balance;

        BankAccount(double balance) { this.balance = balance; }
        double getBalance() { return balance; }

        void deposit(double amount) {
            if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
            balance += amount;
        }

        // before (Lab 3): returned false, and the caller could silently ignore it
        // now: the caller is FORCED to deal with the failure
        void withdraw(double amount) throws InsufficientFundsException {
            if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
            if (amount > balance) {
                throw new InsufficientFundsException(
                        "Cannot withdraw " + amount + "; balance is " + balance, amount - balance);
            }
            balance -= amount;
        }
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount(500);
        try {
            acc.withdraw(200);
            System.out.println("Withdrew 200, balance " + acc.getBalance());
            acc.withdraw(1000);
            System.out.println("not reached");
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
            System.out.println("Short by: " + e.getShortfall());
        }
        System.out.println("Balance unchanged by the failed withdrawal: " + acc.getBalance());
    }
}
