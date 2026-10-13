import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A library member. Owns the rule "at most 3 loans at a time". */
public class Member {
    public static final int MAX_LOANS = 3;

    private final String id;
    private final String name;
    private final List<Loan> loans = new ArrayList<>();

    public Member(String id, String name) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("Member id required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Member name required");
        this.id = id.trim();
        this.name = name.trim().replace(',', ' ');
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<Loan> getLoans() { return Collections.unmodifiableList(loans); }   // read-only view

    public void checkCanBorrow() throws MemberLimitExceededException {
        if (loans.size() >= MAX_LOANS) throw new MemberLimitExceededException(id, MAX_LOANS);
    }

    void addLoan(Loan loan) { loans.add(loan); }
    void removeLoan(Loan loan) { loans.remove(loan); }

    @Override
    public String toString() { return id + " " + name + " (" + loans.size() + "/" + MAX_LOANS + " loans)"; }
}
