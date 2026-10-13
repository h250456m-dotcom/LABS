/** Lab 2, Exercise 5 (Challenge): Temperature with a private constructor and static factories. */
public class Lab02Ex5 {

    static class Temperature {
        private final double celsius;

        private Temperature(double celsius) {   // private: callers must use a factory
            this.celsius = celsius;
        }

        static Temperature fromCelsius(double c) {
            return new Temperature(c);
        }

        static Temperature fromFahrenheit(double f) {
            return new Temperature((f - 32) * 5.0 / 9.0);
        }

        double getCelsius() { return celsius; }
        double getFahrenheit() { return celsius * 9.0 / 5.0 + 32; }

        @Override
        public String toString() {
            return String.format("%.1f C = %.1f F", getCelsius(), getFahrenheit());
        }
    }

    public static void main(String[] args) {
        Temperature boiling = Temperature.fromCelsius(100);
        Temperature body = Temperature.fromFahrenheit(98.6);
        System.out.println(boiling);
        System.out.println(body);
        // new Temperature(5);   // COMPILE ERROR: constructor is private
    }
}
