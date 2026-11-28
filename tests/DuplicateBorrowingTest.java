import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DuplicateBorrowingTest {
    @BeforeEach void setUp() {
        LibraryTestFixture.resetLibrary();
        LibraryTestFixture.addBook(701, "9780134685991", "Effective Java", "Joshua Bloch", 3);
    }
    @Test void memberCannotBorrowSameBookTwice() {
        assertTrue(books.issueBook(701, "Ali"));
        assertFalse(books.issueBook(701, "Ali"));
        assertEquals(2, books.searchBook("Effective Java").getBook_Quantity());
        assertEquals(1, books.getMemberBorrowedCount("Ali"));
    }
    @Test void differentMembersCanBorrowDifferentCopies() {
        assertTrue(books.issueBook(701, "Ali"));
        assertTrue(books.issueBook(701, "Sara"));
        assertEquals(1, books.searchBook("Effective Java").getBook_Quantity());
    }
    @Test void memberCanBorrowAgainAfterReturningBook() {
        assertTrue(books.issueBook(701, "Ali"));
        assertEquals(2, books.searchBook("Effective Java").getBook_Quantity());
        assertEquals(1, books.getMemberBorrowedCount("Ali"));

        assertTrue(books.returnBook(701, "Ali"));
        assertEquals(3, books.searchBook("Effective Java").getBook_Quantity());
        assertEquals(0, books.getMemberBorrowedCount("Ali"));

        assertTrue(books.issueBook(701, "Ali"));
        assertEquals(2, books.searchBook("Effective Java").getBook_Quantity());
        assertEquals(1, books.getMemberBorrowedCount("Ali"));
    }
}
