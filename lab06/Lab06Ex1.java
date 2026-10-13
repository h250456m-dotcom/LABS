/** Lab 6, Exercise 1 (Basic): abstract Shape with area() and perimeter(). */
public class Lab06Ex1 {

    static abstract class Shape {
        abstract double area();
        abstract double perimeter();

        void describe() {                       // concrete method shared by all shapes
            System.out.printf("%-10s area=%8.2f perimeter=%8.2f%n",
                    getClass().getSimpleName(), area(), perimeter());
        }
    }

    static class Circle extends Shape {
        private final double r;
        Circle(double r) { this.r = r; }
        @Override double area() { return Math.PI * r * r; }
        @Override double perimeter() { return 2 * Math.PI * r; }
    }

    static class Rectangle extends Shape {
        protected final double w, h;
        Rectangle(double w, double h) { this.w = w; this.h = h; }
        @Override double area() { return w * h; }
        @Override double perimeter() { return 2 * (w + h); }
    }

    static class Square extends Rectangle {
        Square(double side) { super(side, side); }
    }

    public static void main(String[] args) {
        // Shape s = new Shape();   // COMPILE ERROR: Shape is abstract
        Shape[] shapes = { new Circle(5), new Rectangle(4, 6), new Square(3) };
        for (Shape s : shapes) {
            s.describe();
        }
    }
}
