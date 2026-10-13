/** Lab 3, Exercises 1 and 2 (Basic): Student with validated marks (0-100) and a derived grade. */
public class Lab03Ex1 {

    static class Student {
        private String name;
        private int marks;

        Student(String name, int marks) {
            setName(name);
            setMarks(marks);      // reuse validation in the constructor
        }

        String getName() { return name; }
        int getMarks() { return marks; }

        void setName(String name) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Name required");
            }
            this.name = name.trim();
        }

        void setMarks(int marks) {
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100, got " + marks);
            }
            this.marks = marks;
        }

        // Exercise 2: grade is DERIVED from marks, not stored in a field
        char getGrade() {
            if (marks >= 70) return 'A';
            if (marks >= 60) return 'B';
            if (marks >= 50) return 'C';
            return 'F';
        }
    }

    public static void main(String[] args) {
        Student s = new Student("Tariro", 78);
        System.out.println(s.getName() + ": " + s.getMarks() + " -> grade " + s.getGrade());

        s.setMarks(55);
        System.out.println(s.getName() + ": " + s.getMarks() + " -> grade " + s.getGrade());

        s.setMarks(41);
        System.out.println(s.getName() + ": " + s.getMarks() + " -> grade " + s.getGrade());

        try {
            s.setMarks(130);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.println("Marks unchanged after rejection: " + s.getMarks());
    }
}
