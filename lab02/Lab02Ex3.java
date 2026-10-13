/** Lab 2, Exercise 3 (Intermediate): Employee with auto-id, overloaded and copy constructors. */
public class Lab02Ex3 {

    static class Employee {
        private static int nextId = 1000;       // static counter
        private final int employeeId;
        private final String name;
        private final String department;
        private final double salary;

        Employee(String name, String department, double salary) {
            this.employeeId = nextId++;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        Employee(String name, String department) {
            this(name, department, 500.0);
        }

        Employee(String name) {
            this(name, "Unassigned");
        }

        // copy constructor: same details, but a NEW unique id
        Employee(Employee other) {
            this(other.name, other.department, other.salary);
        }

        @Override
        public String toString() {
            return String.format("#%d %s, %s, $%.2f", employeeId, name, department, salary);
        }
    }

    public static void main(String[] args) {
        Employee e1 = new Employee("Farai", "IT", 1500);
        Employee e2 = new Employee("Rudo", "Finance");
        Employee e3 = new Employee("Tino");
        Employee e4 = new Employee(e1);       // copy of Farai

        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4 + "   <- copy of e1, different id");
        System.out.println("e1 == e4 ? " + (e1 == e4));
    }
}
