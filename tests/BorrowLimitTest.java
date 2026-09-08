import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BorrowLimitTest {

    @Test
    void testValidBorrowingClass() {

        books.clearLibrary();

        // Create 4 available books
        for (int i = 1; i <= 4; i++) {
            books.addBook(
                new books(
                    300 + i,
                    "Book " + i,
                    "Author " + i,
                    1
                )
            );
        }

        // Member currently has 3 books
        assertTrue(books.issueBook(301, "Ali"));
        assertTrue(books.issueBook(302, "Ali"));
        assertTrue(books.issueBook(303, "Ali"));

        // Fourth book is still allowed because the limit is 5
        assertTrue(books.issueBook(304, "Ali"));
    }

    @Test
    void testInvalidBorrowingClass() {

        books.clearLibrary();

        // Create 6 available books
        for (int i = 1; i <= 6; i++) {
            books.addBook(
                new books(
                    400 + i,
                    "Book " + i,
                    "Author " + i,
                    1
                )
            );
        }

        // Member borrows 5 books
        assertTrue(books.issueBook(401, "Ali"));
        assertTrue(books.issueBook(402, "Ali"));
        assertTrue(books.issueBook(403, "Ali"));
        assertTrue(books.issueBook(404, "Ali"));
        assertTrue(books.issueBook(405, "Ali"));

        // Sixth book must be rejected
        assertFalse(books.issueBook(406, "Ali"));
    }
}