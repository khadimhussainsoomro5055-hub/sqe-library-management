
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

        List<Member> library = List.of(ali, ahmed, sara);

        assertEquals(50.0, Member.borrow_percentile(ahmed, library), 0.0001);
    }

    @Test
    void shouldReturnOneHundredForSingleMemberLibrary() {
        Member ali = createMember(1, "Ali", 5);

        assertEquals(
            100.0,
            Member.borrow_percentile(ali, List.of(ali)),
            0.0001
        );
    }

    @Test
    void shouldReturnZeroForLowestBorrowPercentile() {
        Member ali = createMember(1, "Ali", 5);
        Member ahmed = createMember(2, "Ahmed", 10);
        Member sara = createMember(3, "Sara", 15);

        List<Member> library = List.of(ali, ahmed, sara);

        assertEquals(0.0, Member.borrow_percentile(ali, library), 0.0001);
    }

    @Test
    void shouldReturnOneHundredForHighestBorrowPercentile() {
        Member ali = createMember(1, "Ali", 5);
        Member ahmed = createMember(2, "Ahmed", 10);
        Member sara = createMember(3, "Sara", 15);

        List<Member> library = List.of(ali, ahmed, sara);

        assertEquals(100.0, Member.borrow_percentile(sara, library), 0.0001);
    }

    @Test
    void shouldRejectAnEmptyLibrary() {
        Member ali = createMember(1, "Ali", 5);

        assertThrows(
            IllegalArgumentException.class,
            () -> Member.borrow_percentile(ali, new ArrayList<>())
        );
    }

    @Test
    void shouldRejectANullLibrary() {
        Member ali = createMember(1, "Ali", 5);

        assertThrows(
            IllegalArgumentException.class,
            () -> Member.borrow_percentile(ali, null)
        );
    }
}
