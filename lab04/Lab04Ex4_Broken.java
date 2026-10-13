/** Lab 4, Exercise 4: this file is DELIBERATELY WRONG - it shows the compile error. */
public class Lab04Ex4_Broken {

    static class Animal {
        String name;
        Animal(String name) { this.name = name; }   // only constructor takes a String
    }

    static class Dog extends Animal {
        // no constructor written -> Java inserts: Dog() { super(); }
        // but Animal has no Animal() -> compile error
    }

    public static void main(String[] args) {
        new Dog();
    }
}
