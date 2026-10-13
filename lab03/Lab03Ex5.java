/** Lab 3, Exercise 5 (Challenge): Temperature stored in kelvin, exposed as Celsius/Fahrenheit. */
public class Lab03Ex5 {

    static class Temperature {
        private static final double ABSOLUTE_ZERO_K = 0.0;
        private double kelvin;   // the internal representation: callers never see it

        Temperature(double celsius) {
            setCelsius(celsius);
        }

        double getCelsius() { return kelvin - 273.15; }
        double getFahrenheit() { return getCelsius() * 9.0 / 5.0 + 32; }

        void setCelsius(double celsius) {
            double k = celsius + 273.15;
            if (k < ABSOLUTE_ZERO_K) {
                throw new IllegalArgumentException("Below absolute zero (-273.15 C): " + celsius);
            }
            this.kelvin = k;
        }

        void setFahrenheit(double f) {
            setCelsius((f - 32) * 5.0 / 9.0);
        }
    }

    public static void main(String[] args) {
        Temperature t = new Temperature(25);
        System.out.printf("25 C   -> %.2f C, %.2f F%n", t.getCelsius(), t.getFahrenheit());

        t.setFahrenheit(212);
        System.out.printf("212 F  -> %.2f C, %.2f F%n", t.getCelsius(), t.getFahrenheit());

        try {
            t.setCelsius(-300);
        } catch (IllegalArgumentException e) {
            System.out.println("Rejected: " + e.getMessage());
        }
        System.out.printf("Still valid after rejection: %.2f C%n", t.getCelsius());
        // If the internal unit changed (e.g. to Celsius), only this class changes;
        // callers use getCelsius()/getFahrenheit() and keep working unchanged.
    }
}
