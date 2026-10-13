/** Lab 5, Exercise 3 (Intermediate): Intern added to the Lab 4 hierarchy; the payroll loop is unchanged. */
public class Lab05Ex3 {

    static class Employee {
        private final String name;
        protected double baseSalary;

        Employee(String name, double baseSalary) {
            this.name = name;
            this.baseSalary = baseSalary;
        }
        String getName() { return name; }
        double calculatePay() { return baseSalary; }
    }

    static class Manager extends Employee {
        private final double bonus;
        Manager(String name, double baseSalary, double bonus) {
            super(name, baseSalary);
            this.bonus = bonus;
        }
        @Override double calculatePay() { return super.calculatePay() + bonus; }
    }

    static class Developer extends Employee {
        private final int overtimeHours;
        Developer(String name, double baseSalary, int overtimeHours) {
            super(name, baseSalary);
            this.overtimeHours = overtimeHours;
        }
        @Override double calculatePay() { return baseSalary + overtimeHours * 25.0; }
    }

    // NEW class: added later, the payroll loop below is NOT touched
    static class Intern extends Employee {
        Intern(String name, double stipend) { super(name, stipend); }
        @Override double calculatePay() { return baseSalary * 0.5 + 100; }   // half stipend plus transport
    }

    // the payroll method only knows about Employee
    static double totalPayroll(Employee[] staff) {
        double total = 0;
        for (Employee e : staff) {
            System.out.printf("%-8s %-10s %8.2f%n", e.getName(), e.getClass().getSimpleName(), e.calculatePay());
            total += e.calculatePay();
        }
        return total;
    }

    public static void main(String[] args) {
        Employee[] staff = {
            new Employee("Kudzai", 2000),
            new Manager("Nyasha", 3500, 800),
            new Developer("Tatenda", 3000, 10),
            new Intern("Chipo", 600)
        };
        System.out.printf("Total payroll: %.2f%n", totalPayroll(staff));
    }
}
