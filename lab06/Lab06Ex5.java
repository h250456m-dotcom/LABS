/** Lab 6, Exercise 5 (Challenge): two interfaces with the same default method. */
public class Lab06Ex5 {

    interface English {
        default void hello() { System.out.println("Hello"); }
    }

    interface Shona {
        default void hello() { System.out.println("Mhoro"); }
    }

    // Without overriding hello(), this class would NOT compile:
    // "inherits unrelated defaults for hello() from types English and Shona".
    static class Greeter implements English, Shona {
        @Override
        public void hello() {
            English.super.hello();    // choose one explicitly...
            Shona.super.hello();      // ...or call both
        }
    }

    public static void main(String[] args) {
        new Greeter().hello();
    }
}
