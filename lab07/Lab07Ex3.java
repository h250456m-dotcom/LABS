/** Lab 7, Exercise 3 (Intermediate): checked InvalidAgeException thrown for ages outside 0..120. */
public class Lab07Ex3 {

    static class InvalidAgeException extends Exception {       // extends Exception -> CHECKED
        private final int age;

        InvalidAgeException(String message, int age) {
            super(message);
            this.age = age;
        }
        int getAge() { return age; }
    }

    // 'throws' tells callers they MUST handle the checked exception
    static void validateAge(int age) throws InvalidAgeException {
        if (age < 0 || age > 120) {
            throw new InvalidAgeException("Age must be between 0 and 120", age);
        }
        System.out.println("Age " + age + " is valid");
    }

    public static void main(String[] args) {
        int[] tests = {25, 0, 120, -5, 150};
        for (int t : tests) {
            try {
                validateAge(t);
            } catch (InvalidAgeException e) {
                System.out.println("Invalid age " + e.getAge() + ": " + e.getMessage());
            }
        }
    }
}
