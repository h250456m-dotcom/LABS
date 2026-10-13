import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Lab 8, Exercise 4 (Intermediate): word frequency with a Map, printed by frequency descending. */
public class Lab08Ex4 {

    public static void main(String[] args) {
        String paragraph = "The quick brown fox jumps over the lazy dog. The dog sleeps, "
                + "but the fox is quick and the fox is clever.";

        // normalise: lower case, remove punctuation, split on spaces
        String[] words = paragraph.toLowerCase().replaceAll("[^a-z\\s]", "").split("\\s+");

        Map<String, Integer> freq = new TreeMap<>();     // TreeMap keeps keys alphabetical
        for (String w : words) {
            freq.merge(w, 1, Integer::sum);              // add 1, or start at 1
        }

        List<Map.Entry<String, Integer>> entries = new ArrayList<>(freq.entrySet());
        // sort by count descending; ties are already alphabetical (stable sort of a TreeMap)
        entries.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("Word      Count");
        for (Map.Entry<String, Integer> e : entries) {
            System.out.printf("%-9s %d%n", e.getKey(), e.getValue());
        }
    }
}
