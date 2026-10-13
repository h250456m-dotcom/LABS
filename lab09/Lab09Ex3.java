import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/** Lab 9, Exercise 3 (Intermediate): equals/hashCode by ISBN so a HashSet rejects duplicates. */
public class Lab09Ex3 {

    static class Book {
        private final String isbn;
        private final String title;

        Book(String isbn, String title) { this.isbn = isbn; this.title = title; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Book)) return false;
            return isbn.equals(((Book) o).isbn);     // identity of a book = its ISBN only
        }

        @Override
        public int hashCode() { return Objects.hash(isbn); }   // consistent with equals

        @Override
        public String toString() { return title + " [" + isbn + "]"; }
    }

    // For contrast: equals() overridden but hashCode() forgotten
    static class BrokenBook {
        private final String isbn;
        BrokenBook(String isbn) { this.isbn = isbn; }

        @Override
        public boolean equals(Object o) {
            return o instanceof BrokenBook && isbn.equals(((BrokenBook) o).isbn);
        }
    }

    public static void main(String[] args) {
        Set<Book> shelf = new HashSet<>();
        System.out.println("add Clean Code:        " + shelf.add(new Book("978-0132350884", "Clean Code")));
        System.out.println("add Effective Java:    " + shelf.add(new Book("978-0134685991", "Effective Java")));
        System.out.println("add Clean Code again:  " + shelf.add(new Book("978-0132350884", "Clean Code (2nd copy)")));
        System.out.println("Set size: " + shelf.size() + "  -> " + shelf);

        Set<BrokenBook> broken = new HashSet<>();
        broken.add(new BrokenBook("111"));
        broken.add(new BrokenBook("111"));
        System.out.println("Without hashCode(), the 'duplicate' is accepted: size = " + broken.size());
    }
}
