/** Checked exception: a member tried to hold more than the maximum number of loans. */
public class MemberLimitExceededException extends Exception {
    private final String memberId;

    public MemberLimitExceededException(String memberId, int limit) {
        super("Member " + memberId + " already has the maximum of " + limit + " loans");
        this.memberId = memberId;
    }

    public String getMemberId() { return memberId; }
}
