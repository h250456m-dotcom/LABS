/** Lab 6, Exercise 3 (Intermediate): abstract Account with concrete deposit() and abstract monthlyFee(). */
public class Lab06Ex3 {

    static abstract class Account {
        protected final String number;
        protected double balance;

        Account(String number, double balance) {
            this.number = number;
            this.balance = balance;
        }

        void deposit(double amount) {                    // concrete: shared by all accounts
            if (amount <= 0) throw new IllegalArgumentException("Deposit must be positive");
            balance += amount;
        }

        abstract double monthlyFee();                    // each account type decides its own fee

        void applyMonthlyFee() {
            double fee = monthlyFee();
            balance -= fee;
            System.out.printf("%s (%s): fee %.2f charged, balance %.2f%n",
                    number, getClass().getSimpleName(), fee, balance);
        }
    }

    static class SavingsAccount extends Account {
        SavingsAccount(String number, double balance) { super(number, balance); }
        @Override double monthlyFee() { return 2.00; }                       // flat fee
    }

    static class CurrentAccount extends Account {
        CurrentAccount(String number, double balance) { super(number, balance); }
        @Override double monthlyFee() { return 5.00 + balance * 0.005; }     // base fee + 0.5% of balance
    }

    public static void main(String[] args) {
        Account[] accounts = { new SavingsAccount("SAV-1", 1000), new CurrentAccount("CUR-1", 1000) };
        for (Account a : accounts) {
            a.deposit(500);
            a.applyMonthlyFee();
        }
    }
}
