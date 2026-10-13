import java.util.ArrayList;
import java.util.List;

/** Lab 3, Exercise 4 (Intermediate): an immutable class. */
public class Lab03Ex4 {

    // final class: cannot be subclassed, so no subclass can add mutable behaviour
    static final class ImmutablePerson {
        private final String name;               // final fields: assigned once, in the constructor
        private final int age;
        private final List<String> hobbies;      // mutable type, so it needs defensive copies

        ImmutablePerson(String name, int age, List<String> hobbies) {
            if (name == null || name.isBlank()) throw new IllegalArgumentException("Name required");
            if (age < 0) throw new IllegalArgumentException("Age cannot be negative");
            this.name = name;
            this.age = age;
            this.hobbies = List.copyOf(hobbies); // defensive copy IN
        }

        String getName() { return name; }
        int getAge() { return age; }
        List<String> getHobbies() { return hobbies; }  // already unmodifiable

        // no setters: a "change" produces a NEW object
        ImmutablePerson withAge(int newAge) {
            return new ImmutablePerson(name, newAge, hobbies);
        }

        @Override
        public String toString() { return name + " (" + age + ") " + hobbies; }
    }

    public static void main(String[] args) {
        List<String> hobbies = new ArrayList<>(List.of("chess", "football"));
        ImmutablePerson p = new ImmutablePerson("Nyasha", 20, hobbies);
        System.out.println(p);

        hobbies.add("swimming");     // change the ORIGINAL list afterwards
        System.out.println("After changing the caller's list: " + p);

        ImmutablePerson older = p.withAge(21);
        System.out.println("Original: " + p);
        System.out.println("New copy: " + older);

        try {
            p.getHobbies().add("hacking");
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify hobbies through the getter");
        }
    }
}
