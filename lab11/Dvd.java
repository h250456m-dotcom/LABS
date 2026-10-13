/** A DVD: loaned for only 7 days. */
public class Dvd extends LibraryItem {
    private final int minutes;

    public Dvd(String id, String title, int minutes) {
        super(id, title);
        if (minutes <= 0) throw new IllegalArgumentException("Duration must be positive");
        this.minutes = minutes;
    }

    public int getMinutes() { return minutes; }

    @Override public int loanPeriodDays() { return 7; }
    @Override public String type() { return "DVD"; }
    @Override protected String details() { return minutes + " min"; }
    @Override protected String extraCsvField() { return String.valueOf(minutes); }
}
