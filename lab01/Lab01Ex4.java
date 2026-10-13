/** Lab 1, Exercise 4 (Intermediate): aliasing - Rectangle r2 = r1; change r2.width; print r1.width. */
public class Lab01Ex4 {

    static class Rectangle {
        double width;
        double height;
    }

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle();
        r1.width = 5;
        r1.height = 4;

        Rectangle r2 = r1;      // copies the REFERENCE, not the object
        r2.width = 10;

        System.out.println("r1.width = " + r1.width);   // prediction: 10.0
        System.out.println("r1 == r2 ? " + (r1 == r2)); // true: same object

        // Contrast: a real copy is an independent object
        Rectangle r3 = new Rectangle();
        r3.width = r1.width;
        r3.height = r1.height;
        r3.width = 99;
        System.out.println("After changing the real copy r3: r1.width = " + r1.width);
    }
}
