package books_management;

import java.util.List;
import java.util.Objects;

public class Member {

    private final int memberId;
    private final String memberName;
    private int borrowedBooks;

    public Member(int memberId, String memberName, int borrowedBooks) {
        if (memberId < 0) {
            throw new IllegalArgumentException("Member ID cannot be negative.");
        }
        if (memberName == null || memberName.trim().isEmpty()) {
            throw new IllegalArgumentException("Member name cannot be empty.");
        }
        if (borrowedBooks < 0) {
            throw new IllegalArgumentException("Borrowed book count cannot be negative.");
        }
        this.memberId = memberId;
        this.memberName = memberName.trim();
        this.borrowedBooks = borrowedBooks;
    }

    public int getMemberId() { return memberId; }
    public String getMemberName() { return memberName; }
    public int getBorrowedBooks() { return borrowedBooks; }

    public void setBorrowedBooks(int borrowedBooks) {
        if (borrowedBooks < 0) {
            throw new IllegalArgumentException("Borrowed book count cannot be negative.");
        }
        this.borrowedBooks = borrowedBooks;
    }

    /**
     * Calculates a member's relative borrowing percentile within a library.
     * Members with equal borrowing counts receive the same percentile.
     *
     * @param member member whose percentile is requested
     * @param library complete list of members used for comparison
     * @return percentile from 0.0 (lowest) to 100.0 (highest)
     * @throws IllegalArgumentException when inputs are null, the library is empty,
     *         or the member is not part of the supplied library
     */
    public static double borrow_percentile(Member member, List<Member> library) {
        if (member == null) {
            throw new IllegalArgumentException("Member cannot be null.");
        }
        if (library == null || library.isEmpty()) {
            throw new IllegalArgumentException("Library cannot be null or empty.");
        }

        boolean found = library.stream()
                .filter(Objects::nonNull)
                .anyMatch(candidate -> candidate.getMemberId() == member.getMemberId());
        if (!found) {
            throw new IllegalArgumentException("Member is not part of the library.");
        }
        if (library.size() == 1) {
            return 100.0;
        }

        long membersWithFewerLoans = library.stream()
                .filter(Objects::nonNull)
                .filter(candidate -> candidate.getBorrowedBooks() < member.getBorrowedBooks())
                .count();

        return (membersWithFewerLoans * 100.0) / (library.size() - 1);
    }
}
