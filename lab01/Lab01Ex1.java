/** Lab 1, Exercise 1 (Basic): Car class with brand, year, mileage and display(). */
public class Lab01Ex1 {

    static class Car {
        String brand;
        int year;
        double mileage;

        void display() {
            System.out.println("Brand: " + brand + ", Year: " + year + ", Mileage: " + mileage + " km");
        }
    }

    public static void main(String[] args) {
        Car c1 = new Car();
        c1.brand = "Toyota";
        c1.year = 2018;
        c1.mileage = 85000.5;

        Car c2 = new Car();
        c2.brand = "Honda";
        c2.year = 2005;
        c2.mileage = 190250;

        Car c3 = new Car();
        c3.brand = "Mercedes";
        c3.year = 1985;
        c3.mileage = 310000;

        c1.display();
        c2.display();
        c3.display();
    }
}
