import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** Lab 6, Exercise 4 (Intermediate): Comparable (by name) plus a Comparator (by marks). */
public class Lab06Ex4 {

    static class Student implements Comparable<Student> {
        private final String name;
        private final int marks;

        Student(String name, int marks) { this.name = name; this.marks = marks; }
        String getName() { return name; }
        int getMarks() { return marks; }

        @Override
        public int compareTo(Student other) {           // natural order: alphabetical by name
            return name.compareTo(other.name);
        }

        @Override
        public String toString() { return name + "(" + marks + ")"; }
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>(List.of(
                new Student("Tendai", 72), new Student("Anesu", 85),
                new Student("Rudo", 64), new Student("Blessing", 91)));

        Collections.sort(list);                          // uses compareTo
        System.out.println("By name (Comparable):      " + list);

        Comparator<Student> byMarksDesc = Comparator.comparingInt(Student::getMarks).reversed();
        list.sort(byMarksDesc);                          // external Comparator
        System.out.println("By marks high-low (Comparator): " + list);
    }
}
