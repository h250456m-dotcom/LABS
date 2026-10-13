/** Lab 1, Exercise 2 (Basic): add isAntique() - true when the car is more than 25 years old. */
public class Lab01Ex2 {

    static class Car {
        static final int CURRENT_YEAR = 2026; // fixed so the output is repeatable
        String brand;
        int year;
        double mileage;

        Car(String brand, int year, double mileage) {
            this.brand = brand;
            this.year = year;
            this.mileage = mileage;
        }

        void display() {
            System.out.println(brand + " (" + year + "), " + mileage + " km");
        }

        // "more than 25 years old" means strictly greater than 25
        boolean isAntique() {
            return CURRENT_YEAR - year > 25;
        }
    }

    public static void main(String[] args) {
        Car[] cars = {
            new Car("Mercedes", 1985, 310000),
            new Car("Honda", 2001, 150000),   // exactly 25 years old -> NOT antique
            new Car("Toyota", 2018, 85000.5)
        };
        for (Car c : cars) {
            c.display();
            System.out.println("   Antique? " + c.isAntique());
        }
    }
}
