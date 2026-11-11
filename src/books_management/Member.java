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

    public static double borrow_percentile(Member member, List<Member> library) {
        int rank = 0;

        for (Member m : library) {
            if (m.getBorrowedBooks() <= member.getBorrowedBooks()) {
                rank++;
            }
        }

return ((double) rank / library.size()) * 100;
    }
}
