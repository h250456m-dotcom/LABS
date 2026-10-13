import java.util.List;

/** Lab 10, Exercise 4 (Intermediate): streams - students above the class average, sorted by name. */
public class Lab10Ex4 {

    record Student(String name, double mark) { }

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Tariro", 78), new Student("Farai", 55),
                new Student("Rudo", 91), new Student("Anesu", 64),
                new Student("Blessing", 82), new Student("Chipo", 47));

        double average = students.stream()
                .mapToDouble(Student::mark)
                .average()
                .orElse(0);
        System.out.printf("Class average: %.2f%n", average);

        System.out.println("Above average (alphabetical):");
        students.stream()
                .filter(s -> s.mark() > average)          // keep above-average students
                .map(Student::name)                       // Student -> String
                .sorted()                                 // alphabetical
                .forEach(n -> System.out.println("  " + n));
    }
}
