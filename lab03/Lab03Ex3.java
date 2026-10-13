import java.util.ArrayList;
import java.util.List;

/** Lab 3, Exercise 3 (Intermediate): BankAccount with a transaction history (unmodifiable copy). */
public class Lab03Ex3 {

    static class BankAccount {
        private final String accountNumber;
        private double balance;
        private final List<String> history = new ArrayList<>();

        BankAccount(String accountNumber, double openingBalance) {
            if (openingBalance < 0) throw new IllegalArgumentException("Opening balance cannot be negative");
            this.accountNumber = accountNumber;
            this.balance = openingBalance;
            history.add(String.format("OPEN     %.2f", openingBalance));
        }

        void deposit(double amount) {
            if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
            balance += amount;
            history.add(String.format("DEPOSIT  %.2f", amount));
        }

        boolean withdraw(double amount) {
            if (amount <= 0) throw new IllegalArgumentException("Withdrawal must be positive");
            if (amount > balance) {
                history.add(String.format("DECLINED %.2f", amount));
                return false;
            }
            balance -= amount;
            history.add(String.format("WITHDRAW %.2f", amount));
            return true;
        }

        double getBalance() { return balance; }

        // returns an UNMODIFIABLE COPY: callers can read it but cannot corrupt the real history
        List<String> getHistory() { return List.copyOf(history); }
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("ACC-001", 500);
        acc.deposit(250);
        acc.withdraw(100);
        acc.withdraw(5000);

        System.out.println("Balance: " + acc.getBalance());
        System.out.println("Transaction history:");
        for (String line : acc.getHistory()) {
            System.out.println("  " + line);
        }

        try {
            acc.getHistory().clear();    // attempt to tamper with the history
        } catch (UnsupportedOperationException e) {
            System.out.println("Tampering blocked: history is read-only");
        }
        System.out.println("Entries still stored: " + acc.getHistory().size());
    }
}
