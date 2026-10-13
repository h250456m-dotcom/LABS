/** Lab 7, Exercise 2 (Basic): array access with ArrayIndexOutOfBoundsException handled. */
public class Lab07Ex2 {

    static int safeGet(int[] data, int index) {
        try {
            return data[index];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("  Index " + index + " is invalid: " + e.getMessage());
            return -1;                 // sentinel value for "not found"
        }
    }

    public static void main(String[] args) {
        int[] marks = {55, 68, 72, 90};
        System.out.println("marks[2] = " + safeGet(marks, 2));
        System.out.println("marks[4] = " + safeGet(marks, 4));
        System.out.println("marks[-1] = " + safeGet(marks, -1));
    }
}
