/** Lab 6, Exercise 2 (Basic): Flyable interface implemented by unrelated classes. */
public class Lab06Ex2 {

    interface Flyable {
        void fly();
    }

    static class Animal { }                       // Bird extends Animal
    static class Machine { }                      // Aeroplane extends Machine

    static class Bird extends Animal implements Flyable {
        @Override public void fly() { System.out.println("Bird flaps its wings and flies"); }
    }

    static class Aeroplane extends Machine implements Flyable {
        @Override public void fly() { System.out.println("Aeroplane takes off using jet engines"); }
    }

    public static void main(String[] args) {
        Flyable[] things = { new Bird(), new Aeroplane() };   // no shared superclass needed
        for (Flyable f : things) {
            f.fly();
        }
    }
}
