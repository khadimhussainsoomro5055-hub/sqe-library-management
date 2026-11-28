package tests;

import books_management.books;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISBNEnforcementTest {
    @BeforeEach void setUp() { LibraryTestFixture.resetLibrary(); }
    @Test void acceptsValidExplicitIsbn() {
        books book = new books(801, "9780134685991", "Effective Java", "Joshua Bloch", 2);
        assertTrue(books.addBook(book));
        assertEquals("9780134685991", book.getIsbn());
    }
    @Test void rejectsMalformedIsbnAtConstruction() {
        assertThrows(IllegalArgumentException.class,
                () -> new books(802, "12345", "Invalid", "Author", 1));
    }
    @Test void rejectsDuplicateIsbnWhenCatalogIdsDiffer() {
        assertTrue(books.addBook(new books(803, "9780134685991", "First", "Author", 1)));
        assertFalse(books.addBook(new books(804, "9780134685991", "Second", "Author", 1)));
        assertEquals(1, books.totalAvailableCopies());
    }
    @Test void rejectsNullAndNonNumericIsbn() {
        assertFalse(books.validateISBN(null));
        assertFalse(books.validateISBN("97801346ABC91"));
        assertFalse(books.validateISBN("978-0134685991"));
    }
}
