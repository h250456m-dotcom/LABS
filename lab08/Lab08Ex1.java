import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Lab 8, Exercise 1 (Basic): ArrayList of 10 integers - sum, maximum and reversed list. */
public class Lab08Ex1 {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(12, 45, 7, 23, 56, 89, 34, 5, 67, 21));

        int sum = 0;
        for (int n : nums) sum += n;          // auto-unboxing Integer -> int

        System.out.println("List:    " + nums);
        System.out.println("Sum:     " + sum);
        System.out.println("Maximum: " + Collections.max(nums));

        List<Integer> reversed = new ArrayList<>(nums);   // copy so the original is untouched
        Collections.reverse(reversed);
        System.out.println("Reverse: " + reversed);
    }
}
