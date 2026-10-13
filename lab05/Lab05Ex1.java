import java.util.Arrays;

/** Lab 5, Exercise 1 (Basic): overloaded print() for int, double, String and int[]. */
public class Lab05Ex1 {

    static void print(int x)      { System.out.println("int: " + x); }
    static void print(double x)   { System.out.println("double: " + x); }
    static void print(String x)   { System.out.println("String: " + x); }
    static void print(int[] x)    { System.out.println("int[]: " + Arrays.toString(x)); }

    public static void main(String[] args) {
        print(42);                    // print(int)
        print(3.14);                  // print(double)
        print("Hello");               // print(String)
        print(new int[]{1, 2, 3});    // print(int[])
        print('A');                   // char is widened to int, so print(int) runs
        print(7L);                    // long widens to double: print(double)
    }
}
