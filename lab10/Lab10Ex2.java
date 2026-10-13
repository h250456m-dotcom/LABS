import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/** Lab 10, Exercise 2 (Basic): a Predicate and removeIf delete all even numbers. */
public class Lab10Ex2 {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println("Before: " + nums);

        Predicate<Integer> isEven = n -> n % 2 == 0;     // lambda implementing Predicate<Integer>
        nums.removeIf(isEven);

        System.out.println("After removing evens: " + nums);
    }
}
