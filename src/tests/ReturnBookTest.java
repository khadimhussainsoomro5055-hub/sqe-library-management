package tests;

import books_management.books;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReturnBookTest {
    @BeforeEach void setUp() {
        LibraryTestFixture.resetLibrary();
        LibraryTestFixture.addBook(1001, "The Pragmatic Programmer", "Andrew Hunt", 1);
    }
    @Test void returningBorrowedBookRestoresAvailableQuantity() {
        assertTrue(books.issueBook(1001, "Ali"));
        assertEquals(0, books.searchBook("The Pragmatic Programmer").getBook_Quantity());
        assertTrue(books.returnBook(1001, "Ali"));
        assertEquals(1, books.searchBook("The Pragmatic Programmer").getBook_Quantity());
    }
    @Test void cannotReturnBookBorrowedByAnotherMember() {
        assertTrue(books.issueBook(1001, "Ali"));
        assertFalse(books.returnBook(1001, "Sara"));
        assertEquals(0, books.searchBook("The Pragmatic Programmer").getBook_Quantity());
    }
    @Test void cannotReturnSameLoanTwice() {
        assertTrue(books.issueBook(1001, "Ali"));
        assertTrue(books.returnBook(1001, "Ali"));
        assertFalse(books.returnBook(1001, "Ali"));
        assertEquals(1, books.searchBook("The Pragmatic Programmer").getBook_Quantity());
    }
}
