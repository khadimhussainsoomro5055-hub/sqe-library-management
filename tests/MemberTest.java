import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class MemberTest {

    private Member createMember(int id, String name, int borrowedBooks) {
        return new Member(id, name, borrowedBooks);
    }

    @Test
    void shouldCalculateMiddleBorrowPercentile() {
        Member ali = createMember(1, "Ali", 5);
        Member ahmed = createMember(2, "Ahmed", 10);
        Member sara = createMember(3, "Sara", 15);
        assertEquals(50.0, Member.borrow_percentile(ahmed, List.of(ali, ahmed, sara)), 0.0001);
    }

    @Test
    void shouldReturnOneHundredForSingleMemberLibrary() {
        Member ali = createMember(1, "Ali", 5);
        assertEquals(100.0, Member.borrow_percentile(ali, List.of(ali)), 0.0001);
    }

    @Test
    void shouldReturnZeroForLowestBorrowPercentile() {
        Member ali = createMember(1, "Ali", 5);
        Member ahmed = createMember(2, "Ahmed", 10);
        Member sara = createMember(3, "Sara", 15);
        assertEquals(0.0, Member.borrow_percentile(ali, List.of(ali, ahmed, sara)), 0.0001);
    }

    @Test
    void shouldReturnOneHundredForHighestBorrowPercentile() {
        Member ali = createMember(1, "Ali", 5);
        Member ahmed = createMember(2, "Ahmed", 10);
        Member sara = createMember(3, "Sara", 15);
        assertEquals(100.0, Member.borrow_percentile(sara, List.of(ali, ahmed, sara)), 0.0001);
    }

    @Test
    void tiedMembersReceiveTheSamePercentile() {
        Member ali = createMember(1, "Ali", 5);
        Member ahmed = createMember(2, "Ahmed", 5);
        Member sara = createMember(3, "Sara", 15);
        List<Member> members = List.of(ali, ahmed, sara);
        assertEquals(Member.borrow_percentile(ali, members),
                Member.borrow_percentile(ahmed, members), 0.0001);
    }

    @Test
    void shouldRejectAnEmptyLibrary() {
        Member ali = createMember(1, "Ali", 5);
        assertThrows(IllegalArgumentException.class,
                () -> Member.borrow_percentile(ali, new ArrayList<>()));
    }

    @Test
    void shouldRejectANullLibrary() {
        Member ali = createMember(1, "Ali", 5);
        assertThrows(IllegalArgumentException.class,
                () -> Member.borrow_percentile(ali, null));
    }

    @Test
    void shouldRejectNullMemberAndMemberOutsideLibrary() {
        Member ali = createMember(1, "Ali", 5);
        assertThrows(IllegalArgumentException.class,
                () -> Member.borrow_percentile(null, List.of(ali)));
        Member sara = createMember(2, "Sara", 3);
        assertThrows(IllegalArgumentException.class,
                () -> Member.borrow_percentile(sara, List.of(ali)));
    }

    @Test
    void rejectsNegativeBorrowedBookCounts() {
        assertThrows(IllegalArgumentException.class,
                () -> createMember(3, "Ahmed", -1));
        Member ali = createMember(1, "Ali", 1);
        assertThrows(IllegalArgumentException.class, () -> ali.setBorrowedBooks(-1));
    }
}
