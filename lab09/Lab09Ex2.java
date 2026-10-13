/** Lab 9, Exercise 2 (Basic): record Student(String name, int age). */
public class Lab09Ex2 {

    record Student(String name, int age) { }     // constructor, accessors, equals, hashCode, toString for free

    public static void main(String[] args) {
        Student s1 = new Student("Tariro", 19);
        Student s2 = new Student("Tariro", 19);
        Student s3 = new Student("Farai", 21);

        System.out.println(s1);                                  // generated toString
        System.out.println("s1.equals(s2) = " + s1.equals(s2)); // true: records compare by VALUE
        System.out.println("s1 == s2      = " + (s1 == s2));    // false: different objects
        System.out.println("s1.equals(s3) = " + s1.equals(s3));
        System.out.println("Component name() = " + s3.name() + ", age() = " + s3.age());
    }
}
