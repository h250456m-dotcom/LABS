/** Lab 8, Exercise 5: DELIBERATELY WRONG - why int[] will not work with the generic bubbleSort. */
public class Lab08Ex5_Broken {

    static <T extends Comparable<T>> void bubbleSort(T[] arr) { }

    public static void main(String[] args) {
        int[] primitives = {3, 1, 2};
        bubbleSort(primitives);        // int[] is NOT a T[] because T must be a reference type
    }
}
