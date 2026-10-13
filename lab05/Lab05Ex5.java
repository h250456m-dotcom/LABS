/** Lab 5, Exercise 5 (Challenge): instanceof pattern matching versus a polymorphic process() method. */
public class Lab05Ex5 {

    // ---------- Design 1: one class decides using instanceof ----------
    static class Payment {
        final double amount;
        Payment(double amount) { this.amount = amount; }
    }
    static class CardPayment extends Payment {
        final String cardNumber;
        CardPayment(double amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
    }
    static class MobileMoneyPayment extends Payment {
        final String phone;
        MobileMoneyPayment(double amount, String phone) { super(amount); this.phone = phone; }
    }

    static class PaymentProcessor {
        void pay(Payment p) {
            if (p instanceof CardPayment c) {                  // test AND cast in one step
                System.out.println("Charging card ending " + c.cardNumber.substring(c.cardNumber.length() - 4)
                        + " for " + c.amount);
            } else if (p instanceof MobileMoneyPayment m) {
                System.out.println("Sending mobile money prompt to " + m.phone + " for " + m.amount);
            } else {
                System.out.println("Unknown payment type");
            }
        }
    }

    // ---------- Design 2: each class knows how to process itself ----------
    static abstract class Payment2 {
        final double amount;
        Payment2(double amount) { this.amount = amount; }
        abstract void process();
    }
    static class CardPayment2 extends Payment2 {
        final String cardNumber;
        CardPayment2(double amount, String cardNumber) { super(amount); this.cardNumber = cardNumber; }
        @Override void process() {
            System.out.println("Charging card ending " + cardNumber.substring(cardNumber.length() - 4) + " for " + amount);
        }
    }
    static class MobileMoneyPayment2 extends Payment2 {
        final String phone;
        MobileMoneyPayment2(double amount, String phone) { super(amount); this.phone = phone; }
        @Override void process() {
            System.out.println("Sending mobile money prompt to " + phone + " for " + amount);
        }
    }
    // A new type (e.g. BankTransfer2) only needs its own process(); nothing else changes.

    public static void main(String[] args) {
        System.out.println("--- Design 1: instanceof ---");
        PaymentProcessor proc = new PaymentProcessor();
        proc.pay(new CardPayment(120, "4111111111111234"));
        proc.pay(new MobileMoneyPayment(45, "+263771234567"));
        proc.pay(new Payment(10));

        System.out.println("--- Design 2: polymorphism ---");
        Payment2[] batch = { new CardPayment2(120, "4111111111111234"), new MobileMoneyPayment2(45, "+263771234567") };
        for (Payment2 p : batch) {
            p.process();
        }
    }
}
