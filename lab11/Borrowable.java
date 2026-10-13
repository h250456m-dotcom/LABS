/** Contract for anything that can be borrowed from the library. */
public interface Borrowable {
    void borrow(Member member) throws ItemNotAvailableException;
    void giveBack();
    boolean isAvailable();
}
