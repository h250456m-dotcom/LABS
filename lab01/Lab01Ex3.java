/** Lab 1, Exercise 3 (Intermediate): Rectangle with area()/perimeter(); find the largest of 5. */
public class Lab01Ex3 {

    static class Rectangle {
        double width;
        double height;

        double area() { return width * height; }
        double perimeter() { return 2 * (width + height); }
    }

    static Rectangle make(double w, double h) {
        Rectangle r = new Rectangle();
        r.width = w;
        r.height = h;
        return r;
    }

    public static void main(String[] args) {
        Rectangle[] rects = {
            make(2, 3), make(5, 4), make(10, 1), make(6, 6), make(3.5, 7)
        };

        Rectangle largest = rects[0];
        for (int i = 0; i < rects.length; i++) {
            System.out.printf("Rectangle %d: %.1f x %.1f  area=%.2f  perimeter=%.2f%n",
                    i + 1, rects[i].width, rects[i].height, rects[i].area(), rects[i].perimeter());
            if (rects[i].area() > largest.area()) {
                largest = rects[i];
            }
        }
        System.out.printf("Largest: %.1f x %.1f with area %.2f%n",
                largest.width, largest.height, largest.area());
    }
}
