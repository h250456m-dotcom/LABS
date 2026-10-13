/** A printed book: loaned for 14 days. */
public class Book extends LibraryItem {
    private final String author;

    public Book(String id, String title, String author) {
        super(id, title);
        if (author == null || author.isBlank()) throw new IllegalArgumentException("Author required");
        this.author = author.trim().replace(',', ' ');
    }

    public String getAuthor() { return author; }

    @Override public int loanPeriodDays() { return 14; }
    @Override public String type() { return "BOOK"; }
    @Override protected String details() { return "by " + author; }
    @Override protected String extraCsvField() { return author; }
}
