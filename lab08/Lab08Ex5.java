import java.util.Arrays;

/** Lab 8, Exercise 5 (Challenge): generic bubbleSort on Integer[] and String[]. */
public class Lab08Ex5 {

    // T must be Comparable<T> so that compareTo is available
    static <T extends Comparable<T>> void bubbleSort(T[] arr) {
        for (int pass = 0; pass < arr.length - 1; pass++) {
            boolean swapped = false;
            for (int i = 0; i < arr.length - 1 - pass; i++) {
                if (arr[i].compareTo(arr[i + 1]) > 0) {
                    T temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;       // already sorted
        }
    }

    public static void main(String[] args) {
        Integer[] nums = {42, 7, 19, 3, 88, 25};
        String[] names = {"Tendai", "Anesu", "Rudo", "Blessing", "Chipo"};

        bubbleSort(nums);
        bubbleSort(names);

        System.out.println("Sorted Integer[]: " + Arrays.toString(nums));
        System.out.println("Sorted String[]:  " + Arrays.toString(names));
    }
}
