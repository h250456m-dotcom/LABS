/** Abstract base for everything the library lends: shared state plus per-type behaviour. */
public abstract class LibraryItem implements Borrowable {
    private final String id;
    private final String title;
    private boolean available = true;

    protected LibraryItem(String id, String title) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Item id required");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title required");
        this.id = id.trim();
        this.title = title.trim().replace(',', ' ');   // commas would break the CSV format
    }

    // ---- behaviour that differs per subclass (polymorphism) ----
    public abstract int loanPeriodDays();
    public abstract String type();
    protected abstract String details();
    protected abstract String extraCsvField();

    // ---- Borrowable ----
    @Override
    public void borrow(Member member) throws ItemNotAvailableException {
        if (!available) throw new ItemNotAvailableException(id);
        available = false;
    }

    @Override
    public void giveBack() { available = true; }

    @Override
    public boolean isAvailable() { return available; }

    void setAvailable(boolean available) { this.available = available; }   // used when loading from file

    public String getId() { return id; }
    public String getTitle() { return title; }

    public String toCsv() { return type() + "," + id + "," + title + "," + extraCsvField(); }

    @Override
    public String toString() {
        return String.format("%-5s %-5s %-30s %-22s %s", type(), id, title, details(),
                available ? "available" : "ON LOAN");
    }
}
