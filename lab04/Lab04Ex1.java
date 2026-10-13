/** Lab 4, Exercises 1 and 2 (Basic): Person, Student and Teacher sharing one inherited display(). */
public class Lab04Ex1 {

    static class Person {
        protected String name;
        protected int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        void display() {                      // written ONCE, inherited by every subclass
            System.out.println("Name: " + name + ", Age: " + age);
        }
    }

    static class Student extends Person {
        private String course;

        Student(String name, int age, String course) {
            super(name, age);
            this.course = course;
        }

        void showCourse() { System.out.println("   Course: " + course); }
    }

    static class Teacher extends Person {
        private String subject;

        Teacher(String name, int age, String subject) {
            super(name, age);
            this.subject = subject;
        }

        void showSubject() { System.out.println("   Teaches: " + subject); }
    }

    public static void main(String[] args) {
        Student s = new Student("Tariro", 19, "BSc Computer Science");
        Teacher t = new Teacher("Mr Moyo", 41, "Object-Oriented Programming");

        s.display();       // inherited from Person
        s.showCourse();
        t.display();       // the SAME inherited code is reused by Teacher
        t.showSubject();
    }
}
