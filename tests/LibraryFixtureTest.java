
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LibraryFixtureTest {

    // Per-test fixture: reset mutable library state before every test.
    @BeforeEach
    void setUp() {
        LibraryTestFixture.resetLibrary();
    }

    @Test
    void testAddValidBookUsingFixture() {
        LibraryTestFixture.addBook(
            101, "Java Programming", "James Gosling", 5
        );

        assertNotNull(books.searchBook("Java Programming"));
    }

    @Test
    void testRejectDuplicateBookIdUsingFixture() {
        LibraryTestFixture.addBook(
            101, "Java Programming", "James Gosling", 5
        );

        boolean result = books.addBook(
            new books(101, "Clean Code", "Robert Martin", 3)
        );

        assertFalse(result);
    }

    @Test
    void testRejectNegativeQuantityUsingFixture() {
        assertThrows(
            IllegalArgumentException.class,
            () -> LibraryTestFixture.addBook(
                102, "Java Programming", "James Gosling", -5
            )
        );
    }

    @Test
    void testIssueAvailableBookUsingFixture() {
        LibraryTestFixture.addBook(
            103, "Operating Systems", "Abraham Silberschatz", 3
        );

        boolean result = books.issueBook(103, "Ali");

        assertTrue(result);

        books foundBook = books.searchBook("Operating Systems");
        assertNotNull(foundBook);
        assertEquals(2, foundBook.getBook_Quantity());
    }
}
