
import java.util.List;

public class Member {

    private int memberId;
    private String memberName;
    private int borrowedBooks;

    public Member(int memberId, String memberName, int borrowedBooks) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.borrowedBooks = borrowedBooks;
    }

    public int getMemberId() {
        return memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public int getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(int borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }

    /**
     * Calculates the borrow percentile of a member in the library.
     *
     * @param member the member whose percentile is calculated
     * @param library the list of library members
     * @return the borrow percentile as a percentage
     * @throws IllegalArgumentException if the library is null or empty
     */
    public static double borrow_percentile(Member member, List<Member> library) {

        if (library == null || library.isEmpty()) {
            throw new IllegalArgumentException(
                "Library cannot be null or empty"
            );
        }

        int rank = 0;

        for (Member m : library) {
            if (m.getBorrowedBooks() <= member.getBorrowedBooks()) {
                rank++;
            }
        }

        return ((double) rank / library.size()) * 100;
    }
}
