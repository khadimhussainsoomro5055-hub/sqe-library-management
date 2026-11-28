import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class MemberConstructorEdgeCasesTest {

    @Test
    void constructorRejectsInvalidIdNameAndBorrowCount() {
        assertThrows(IllegalArgumentException.class, () -> new Member(-1, "Ali", 0));
        assertThrows(IllegalArgumentException.class, () -> new Member(1, null, 0));
        assertThrows(IllegalArgumentException.class, () -> new Member(1, "  ", 0));
        assertThrows(IllegalArgumentException.class, () -> new Member(1, "Ali", -1));
    }

    @Test
    void constructorTrimsNameAndSetterUpdatesBorrowCount() {
        Member member = new Member(7, "  Sara  ", 2);
        assertEquals(7, member.getMemberId());
        assertEquals("Sara", member.getMemberName());
        assertEquals(2, member.getBorrowedBooks());
        member.setBorrowedBooks(4);
        assertEquals(4, member.getBorrowedBooks());
    }

    @Test
    void percentileHandlesMemberFoundByIdAndRejectsNullMember() {
        Member original = new Member(1, "Ali", 2);
        Member sameId = new Member(1, "Ali copy", 2);
        assertEquals(100.0, Member.borrow_percentile(sameId, List.of(original)), 0.0001);
        assertThrows(IllegalArgumentException.class,
                () -> Member.borrow_percentile(null, List.of(original)));
    }
}
