import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/** Core service: holds the collections and enforces the business rules. */
public class Library {
    private final Map<String, LibraryItem> items = new TreeMap<>();   // keyed by id, sorted for listing
    private final Map<String, Member> members = new TreeMap<>();
    private final Map<String, Loan> activeLoans = new HashMap<>();    // keyed by item id

    /** "Today" can be overridden with -Dlibrary.today=yyyy-mm-dd so overdue behaviour can be demonstrated. */
    public static LocalDate today() {
        String override = System.getProperty("library.today");
        return override == null ? LocalDate.now() : LocalDate.parse(override);
    }

    public void addItem(LibraryItem item) {
        if (items.containsKey(item.getId())) throw new IllegalArgumentException("Item id already exists: " + item.getId());
        items.put(item.getId(), item);
    }

    public void addMember(Member member) {
        if (members.containsKey(member.getId())) throw new IllegalArgumentException("Member id already exists: " + member.getId());
        members.put(member.getId(), member);
    }

    public Collection<LibraryItem> getItems() { return items.values(); }
    public Collection<Member> getMembers() { return members.values(); }

    public Loan borrow(String memberId, String itemId)
            throws ItemNotAvailableException, MemberLimitExceededException {
        // 1. look up member and item
        Member member = members.get(memberId);
        if (member == null) throw new IllegalArgumentException("No such member: " + memberId);
        LibraryItem item = items.get(itemId);
        if (item == null) throw new IllegalArgumentException("No such item: " + itemId);

        // 2. check the loan limit and availability BEFORE changing any state
        member.checkCanBorrow();
        item.borrow(member);          // throws ItemNotAvailableException; polymorphic Borrowable call

        // 3. record the loan; the due date depends on the item's own loan period
        LocalDate today = today();
        Loan loan = new Loan(itemId, memberId, today, today.plusDays(item.loanPeriodDays()));
        activeLoans.put(itemId, loan);
        member.addLoan(loan);
        return loan;
    }

    public Loan returnItem(String itemId) {
        Loan loan = activeLoans.remove(itemId);
        if (loan == null) throw new IllegalArgumentException("Item " + itemId + " is not on loan");
        members.get(loan.getMemberId()).removeLoan(loan);
        items.get(itemId).giveBack();
        return loan;
    }

    /** Streams: filter, sort, collect. */
    public List<Loan> listOverdue() {
        LocalDate today = today();
        return activeLoans.values().stream()
                .filter(l -> l.isOverdue(today))
                .sorted(Comparator.comparing(Loan::getDueDate).thenComparing(Loan::getItemId))
                .toList();
    }

    public List<LibraryItem> searchByTitle(String text) {
        return items.values().stream()
                .filter(i -> i.getTitle().toLowerCase().contains(text.toLowerCase()))
                .sorted(Comparator.comparing(LibraryItem::getTitle))
                .toList();
    }

    public LibraryItem getItem(String id) { return items.get(id); }
    public Member getMember(String id) { return members.get(id); }

    // ------------------------------------------------------------------ persistence (CSV)
    public void save(Path dir) throws IOException {
        Files.createDirectories(dir);
        List<String> itemLines = new ArrayList<>();
        for (LibraryItem i : items.values()) itemLines.add(i.toCsv());
        Files.write(dir.resolve("items.csv"), itemLines);

        List<String> memberLines = new ArrayList<>();
        for (Member m : members.values()) memberLines.add(m.getId() + "," + m.getName());
        Files.write(dir.resolve("members.csv"), memberLines);

        List<String> loanLines = new ArrayList<>();
        for (Loan l : activeLoans.values()) loanLines.add(l.toCsv());
        Files.write(dir.resolve("loans.csv"), loanLines);
    }

    public void load(Path dir) throws IOException {
        Path itemsFile = dir.resolve("items.csv");
        if (Files.exists(itemsFile)) {
            for (String line : Files.readAllLines(itemsFile)) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                switch (p[0]) {
                    case "BOOK" -> addItem(new Book(p[1], p[2], p[3]));
                    case "DVD" -> addItem(new Dvd(p[1], p[2], Integer.parseInt(p[3])));
                    default -> System.out.println("Skipping unknown item type: " + p[0]);
                }
            }
        }
        Path membersFile = dir.resolve("members.csv");
        if (Files.exists(membersFile)) {
            for (String line : Files.readAllLines(membersFile)) {
                if (line.isBlank()) continue;
                String[] p = line.split(",");
                addMember(new Member(p[0], p[1]));
            }
        }
        Path loansFile = dir.resolve("loans.csv");
        if (Files.exists(loansFile)) {
            for (String line : Files.readAllLines(loansFile)) {
                if (line.isBlank()) continue;
                Loan loan = Loan.fromCsv(line);
                LibraryItem item = items.get(loan.getItemId());
                Member member = members.get(loan.getMemberId());
                if (item == null || member == null) {
                    System.out.println("Skipping loan with unknown item/member: " + line);
                    continue;
                }
                item.setAvailable(false);
                member.addLoan(loan);
                activeLoans.put(loan.getItemId(), loan);
            }
        }
    }
}
