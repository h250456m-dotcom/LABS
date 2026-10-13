import java.util.Scanner;

/** Lab 7, Exercise 1 (Basic): read two integers and divide, handling bad input and division by zero. */
public class Lab07Ex1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean echo = System.console() == null;   // echo piped input so the sample output reads naturally

        while (true) {
            System.out.print("Enter two integers (or q to quit): ");
            if (!in.hasNextLine()) break;
            String line = in.nextLine().trim();
            if (echo) System.out.println(line);
            if (line.equalsIgnoreCase("q")) break;

            try {
                String[] parts = line.split("\\s+");
                if (parts.length != 2) {
                    throw new IllegalArgumentException("Please enter exactly two numbers");
                }
                int a = Integer.parseInt(parts[0]);   // NumberFormatException if not an integer
                int b = Integer.parseInt(parts[1]);
                System.out.println("  " + a + " / " + b + " = " + (a / b));   // ArithmeticException if b == 0
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("  Cannot divide by zero");
            } catch (IllegalArgumentException e) {
                System.out.println("  " + e.getMessage());
            }
        }
        System.out.println("Goodbye");
    }
}
