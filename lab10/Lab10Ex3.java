import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** Lab 10, Exercise 3 (Intermediate): count lines, words and characters in a text file. */
public class Lab10Ex3 {

    public static void main(String[] args) {
        Path file = Path.of("sample.txt");
        try {
            // create a sample file first so the program is self-contained
            Files.write(file, List.of(
                    "Object-oriented programming models real world entities.",
                    "",
                    "Classes are blueprints and objects are instances.",
                    "Java uses four pillars: encapsulation, inheritance, polymorphism and abstraction."));

            List<String> lines = Files.readAllLines(file);

            int lineCount = lines.size();
            int wordCount = 0;
            int charCount = 0;
            for (String line : lines) {
                charCount += line.length();                       // characters, excluding line breaks
                if (!line.isBlank()) {
                    wordCount += line.trim().split("\\s+").length; // words are separated by whitespace
                }
            }

            System.out.println("File: " + file);
            System.out.println("Lines:      " + lineCount);
            System.out.println("Words:      " + wordCount);
            System.out.println("Characters: " + charCount + " (excluding line breaks)");
        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }
}
