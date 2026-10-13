import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/** Lab 10, Exercise 5 (Challenge): load learners.csv, group by grade band, write one report per band. */
public class Lab10Ex5 {

    record Learner(String name, int mark) {
        String band() {
            if (mark >= 70) return "A";
            if (mark >= 50) return "B";
            return "C";
        }
    }

    public static void main(String[] args) {
        Path csv = Path.of("learners.csv");
        try {
            // sample input file (header + data)
            Files.write(csv, List.of(
                    "name,mark",
                    "Tariro,78", "Farai,55", "Rudo,91", "Anesu,64",
                    "Blessing,82", "Chipo,47", "Tendai,70", "Nomsa,33"));

            // load: skip the header, parse each line
            List<Learner> learners = new ArrayList<>();
            List<String> rows = Files.readAllLines(csv);
            for (String line : rows.subList(1, rows.size())) {
                String[] p = line.split(",");
                learners.add(new Learner(p[0].trim(), Integer.parseInt(p[1].trim())));
            }
            System.out.println("Loaded " + learners.size() + " learners from " + csv);

            // group by band with Collectors.groupingBy (TreeMap keeps A, B, C in order)
            Map<String, List<Learner>> byBand = learners.stream()
                    .collect(Collectors.groupingBy(Learner::band, TreeMap::new, Collectors.toList()));

            // one report file per band
            for (Map.Entry<String, List<Learner>> e : byBand.entrySet()) {
                Path report = Path.of("report_band_" + e.getKey() + ".txt");
                List<String> out = new ArrayList<>();
                out.add("Band " + e.getKey() + " report (" + e.getValue().size() + " learners)");
                e.getValue().stream()
                        .sorted((a, b) -> b.mark() - a.mark())
                        .forEach(l -> out.add(String.format("  %-10s %d", l.name(), l.mark())));
                Files.write(report, out);

                System.out.println("\n--- " + report + " ---");
                Files.readAllLines(report).forEach(System.out::println);
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
