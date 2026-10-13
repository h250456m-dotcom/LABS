/** Lab 5, Exercise 2 (Basic): Animal[] holding Cat, Cow and Duck; the loop uses only Animal. */
public class Lab05Ex2 {

    static class Animal {
        String sound() { return "..."; }
    }
    static class Cat extends Animal {
        @Override String sound() { return "Meow"; }
    }
    static class Cow extends Animal {
        @Override String sound() { return "Moo"; }
    }
    static class Duck extends Animal {
        @Override String sound() { return "Quack"; }
    }

    public static void main(String[] args) {
        Animal[] farm = { new Cat(), new Cow(), new Duck(), new Animal() };
        for (Animal a : farm) {
            System.out.println(a.getClass().getSimpleName() + " says " + a.sound());
        }
    }
}
