import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Lab 10, Exercise 1 (Basic): save five typed lines to a file, then read them back. */
public class Lab10Ex1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean echo = System.console() == null;     // echo piped input for readable sample output
        List<String> lines = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            System.out.print("Line " + i + ": ");
            String text = in.nextLine();
            if (echo) System.out.println(text);
            lines.add(text);
        }

        Path file = Path.of("typed_lines.txt");     // relative to the WORKING directory
        try {
            Files.write(file, lines);               // creates or overwrites the file
            System.out.println("\nSaved " + lines.size() + " lines to " + file.toAbsolutePath().getFileName());

            List<String> back = Files.readAllLines(file);
            System.out.println("Reading the file back:");
            for (int i = 0; i < back.size(); i++) {
                System.out.println("  " + (i + 1) + ": " + back.get(i));
            }
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
