import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/** One active loan: which member has which item, and when it is due. */
public class Loan {
    private final String itemId;
    private final String memberId;
    private final LocalDate borrowDate;
    private final LocalDate dueDate;

    public Loan(String itemId, String memberId, LocalDate borrowDate, LocalDate dueDate) {
        if (dueDate.isBefore(borrowDate)) throw new IllegalArgumentException("Due date before borrow date");
        this.itemId = itemId;
        this.memberId = memberId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
    }

    public String getItemId() { return itemId; }
    public String getMemberId() { return memberId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }

    public boolean isOverdue(LocalDate today) { return today.isAfter(dueDate); }
    public long daysOverdue(LocalDate today) { return Math.max(0, ChronoUnit.DAYS.between(dueDate, today)); }

    public String toCsv() { return itemId + "," + memberId + "," + borrowDate + "," + dueDate; }

    public static Loan fromCsv(String line) {
        String[] p = line.split(",");
        return new Loan(p[0], p[1], LocalDate.parse(p[2]), LocalDate.parse(p[3]));
    }

    @Override
    public String toString() {
        return "Item " + itemId + " -> member " + memberId + " (borrowed " + borrowDate + ", due " + dueDate + ")";
    }
}
