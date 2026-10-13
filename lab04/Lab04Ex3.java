/** Lab 4, Exercise 3 (Intermediate): Vehicle -> Car -> ElectricCar, tracing constructor order. */
public class Lab04Ex3 {

    static class Vehicle {
        protected String brand;

        Vehicle(String brand) {
            this.brand = brand;
            System.out.println("1. Vehicle constructor: " + brand);
        }
    }

    static class Car extends Vehicle {
        protected int doors;

        Car(String brand, int doors) {
            super(brand);                       // runs Vehicle's constructor first
            this.doors = doors;
            System.out.println("2. Car constructor: " + doors + " doors");
        }
    }

    static class ElectricCar extends Car {
        private int batteryKwh;

        ElectricCar(String brand, int doors, int batteryKwh) {
            super(brand, doors);                // runs Car's constructor (which runs Vehicle's)
            this.batteryKwh = batteryKwh;
            System.out.println("3. ElectricCar constructor: " + batteryKwh + " kWh battery");
        }
    }

    public static void main(String[] args) {
        System.out.println("Creating: new ElectricCar(\"Tesla\", 4, 75)");
        new ElectricCar("Tesla", 4, 75);
    }
}
