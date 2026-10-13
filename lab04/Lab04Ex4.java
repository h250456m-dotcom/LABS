/** Lab 4, Exercise 4 (Intermediate): the FIXED version - Dog calls super(name) explicitly. */
public class Lab04Ex4 {

    static class Animal {
        String name;
        Animal(String name) {
            this.name = name;
            System.out.println("Animal constructor: " + name);
        }
    }

    static class Dog extends Animal {
        Dog(String name) {
            super(name);   // FIX: explicitly choose the superclass constructor that exists
            System.out.println("Dog constructor: " + name);
        }
    }

    public static void main(String[] args) {
        new Dog("Rex");
    }
}
