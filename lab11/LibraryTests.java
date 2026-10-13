import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

/** Simple console tests (no framework needed). Each check prints PASS or FAIL. */
public class LibraryTests {
    private static int passed = 0, failed = 0;

    private static void check(String name, boolean condition) {
        if (condition) passed++; else failed++;
        System.out.println((condition ? "PASS  " : "FAIL  ") + name);
    }

    public static void main(String[] args) throws Exception {
        System.setProperty("library.today", "2026-10-01");
        Library lib = new Library();
        lib.addItem(new Book("B1", "Clean Code", "Robert Martin"));
        lib.addItem(new Book("B2", "Effective Java", "Joshua Bloch"));
        lib.addItem(new Book("B3", "Java Concurrency", "Brian Goetz"));
        lib.addItem(new Dvd("D1", "The Matrix", 136));
        lib.addMember(new Member("M1", "Tendai"));
        lib.addMember(new Member("M2", "Rudo"));

        // polymorphism: same call, different result per subclass
        check("Book loan period is 14 days", lib.getItem("B1").loanPeriodDays() == 14);
        check("Dvd loan period is 7 days", lib.getItem("D1").loanPeriodDays() == 7);

        Loan book = lib.borrow("M1", "B1");
        check("Book due date = today + 14", book.getDueDate().equals(LocalDate.of(2026, 10, 15)));
        Loan dvd = lib.borrow("M1", "D1");
        check("Dvd due date = today + 7", dvd.getDueDate().equals(LocalDate.of(2026, 10, 8)));
        check("Item becomes unavailable", !lib.getItem("B1").isAvailable());

        boolean notAvailable = false;
        try { lib.borrow("M2", "B1"); } catch (ItemNotAvailableException e) { notAvailable = true; }
        check("Borrowing an item already on loan throws ItemNotAvailableException", notAvailable);

        lib.borrow("M1", "B2");   // M1 now has 3 loans
        boolean limit = false;
        try { lib.borrow("M1", "B3"); } catch (MemberLimitExceededException e) { limit = true; }
        check("4th loan throws MemberLimitExceededException", limit);
        check("Failed borrow leaves the item available", lib.getItem("B3").isAvailable());

        boolean unknown = false;
        try { lib.borrow("M9", "B3"); } catch (IllegalArgumentException e) { unknown = true; }
        check("Unknown member throws IllegalArgumentException", unknown);

        check("Search 'java' is case-insensitive and finds 2 titles", lib.searchByTitle("JAVA").size() == 2);

        // persistence round trip
        Path dir = Files.createTempDirectory("librarytest");
        lib.save(dir);
        Library reloaded = new Library();
        reloaded.load(dir);
        check("Reload restores 4 items and 2 members", reloaded.getItems().size() == 4 && reloaded.getMembers().size() == 2);
        check("Reload restores loans (item B1 still on loan)", !reloaded.getItem("B1").isAvailable());
        check("Reload restores member loan count", reloaded.getMember("M1").getLoans().size() == 3);

        // overdue detection and return
        System.setProperty("library.today", "2026-10-10");
        check("DVD is overdue on 10 Oct, books are not", lib.listOverdue().size() == 1
                && lib.listOverdue().get(0).getItemId().equals("D1"));
        lib.returnItem("D1");
        check("Return makes the item available again", lib.getItem("D1").isAvailable());
        check("No overdue items after the DVD is returned", lib.listOverdue().isEmpty());

        System.out.println("\n" + passed + " passed, " + failed + " failed");
    }
}
