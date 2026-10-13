import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/** Lab 8, Exercise 2 (Basic): remove duplicates from a list of names using a Set. */
public class Lab08Ex2 {

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of(
                "Tendai", "Rudo", "Tendai", "Anesu", "Rudo", "Blessing", "Anesu"));
        System.out.println("With duplicates:    " + names);

        // LinkedHashSet removes duplicates AND keeps first-seen order
        Set<String> unique = new LinkedHashSet<>(names);
        List<String> cleaned = new ArrayList<>(unique);
        System.out.println("Without duplicates: " + cleaned);
        System.out.println("Removed " + (names.size() - cleaned.size()) + " duplicates");
    }
}
