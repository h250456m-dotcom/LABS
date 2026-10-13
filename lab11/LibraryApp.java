import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/** Console menu for the Library Management System. Data is loaded on start and saved on exit. */
public class LibraryApp {
    private static final Scanner IN = new Scanner(System.in);
    private static final boolean ECHO = System.console() == null;   // echo piped input for readable logs

    private static String ask(String prompt) {
        System.out.print(prompt);
        if (!IN.hasNextLine()) return "0";          // end of input behaves like "save and exit"
        String line = IN.nextLine().trim();
        if (ECHO) System.out.println(line);
        return line;
    }

    public static void main(String[] args) {
        Path dataDir = Path.of(args.length > 0 ? args[0] : "library-data");
        Library library = new Library();

        try {
            library.load(dataDir);
            System.out.println("Loaded " + library.getItems().size() + " items and "
                    + library.getMembers().size() + " members from " + dataDir + "  (today = " + Library.today() + ")");
        } catch (IOException | RuntimeException e) {
            System.out.println("Could not load saved data: " + e.getMessage());
        }

        boolean running = true;
        while (running) {
            System.out.println("\nMenu: 1 Add item | 2 Register member | 3 Borrow | 4 Return | "
                    + "5 Overdue | 6 Search | 7 List items | 8 List members | 0 Save & exit");
            String choice = ask("Choice: ");
            try {
                switch (choice) {
                    case "1" -> addItem(library);
                    case "2" -> {
                        String id = ask("Member id: ");
                        String name = ask("Name: ");
                        library.addMember(new Member(id, name));
                        System.out.println("Registered member " + id);
                    }
                    case "3" -> {
                        String memberId = ask("Member id: ");
                        String itemId = ask("Item id: ");
                        Loan loan = library.borrow(memberId, itemId);
                        System.out.println("Borrowed. Due on " + loan.getDueDate() + " ("
                                + library.getItem(itemId).loanPeriodDays() + "-day loan)");
                    }
                    case "4" -> {
                        String itemId = ask("Item id: ");
                        Loan loan = library.returnItem(itemId);
                        long late = loan.daysOverdue(Library.today());
                        System.out.println("Returned." + (late > 0 ? " It was " + late + " day(s) overdue." : " On time."));
                    }
                    case "5" -> listOverdue(library);
                    case "6" -> {
                        String text = ask("Title contains: ");
                        List<LibraryItem> found = library.searchByTitle(text);
                        System.out.println(found.size() + " match(es):");
                        found.forEach(i -> System.out.println("  " + i));
                    }
                    case "7" -> library.getItems().forEach(i -> System.out.println("  " + i));
                    case "8" -> library.getMembers().forEach(m -> System.out.println("  " + m));
                    case "0" -> running = false;
                    default -> System.out.println("Unknown option: " + choice);
                }
            } catch (ItemNotAvailableException | MemberLimitExceededException e) {
                System.out.println("Cannot complete: " + e.getMessage());
            } catch (IllegalArgumentException e) {            // validation errors and NumberFormatException
                System.out.println("Error: " + e.getMessage());
            }
        }

        try {
            library.save(dataDir);
            System.out.println("Data saved to " + dataDir + ". Goodbye.");
        } catch (IOException e) {
            System.out.println("Could not save data: " + e.getMessage());
        }
    }

    private static void addItem(Library library) {
        String type = ask("Type (B = Book, D = Dvd): ").toUpperCase();
        if (!type.equals("B") && !type.equals("D")) {
            throw new IllegalArgumentException("Type must be B or D");     // fail fast, before asking more
        }
        String id = ask("Item id: ");
        String title = ask("Title: ");
        if (type.equals("B")) {
            library.addItem(new Book(id, title, ask("Author: ")));
        } else {
            library.addItem(new Dvd(id, title, Integer.parseInt(ask("Duration (minutes): "))));
        }
        System.out.println("Added " + library.getItem(id.trim()).type().toLowerCase() + " " + id);
    }

    private static void listOverdue(Library library) {
        List<Loan> overdue = library.listOverdue();
        if (overdue.isEmpty()) {
            System.out.println("No overdue items.");
            return;
        }
        System.out.println(overdue.size() + " overdue item(s) as of " + Library.today() + ":");
        for (Loan l : overdue) {
            System.out.println("  " + library.getItem(l.getItemId()).getTitle() + " -> "
                    + library.getMember(l.getMemberId()).getName() + ", due " + l.getDueDate()
                    + ", " + l.daysOverdue(Library.today()) + " day(s) overdue");
        }
    }
}
