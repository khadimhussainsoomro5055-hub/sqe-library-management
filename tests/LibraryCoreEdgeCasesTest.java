import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LibraryCoreEdgeCasesTest {

    @TempDir
    Path tempDir;

    @BeforeEach
    void resetCatalog() {
        LibraryTestFixture.resetLibrary();
    }

    @Test
    void constructorRejectsInvalidIdentifiersAndBookDetails() {
        assertThrows(IllegalArgumentException.class,
                () -> new books(-1, "9780134685991", "Title", "Author", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new books(1, (String) null, "Title", "Author", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new books(1, "9780134685991", null, "Author", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new books(1, "9780134685991", "   ", "Author", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new books(1, "9780134685991", "Title", null, 1));
        assertThrows(IllegalArgumentException.class,
                () -> new books(1, "9780134685991", "Title", "   ", 1));
        assertThrows(IllegalArgumentException.class,
                () -> new books(1, "9780134685991", "Title", "Author", -1));
    }

    @Test
    void constructorTrimsTextAndLegacyConstructorCreatesAnIsbn() {
        books explicit = new books(1, "9780134685991", "  Clean Code  ", "  Robert Martin  ", 2);
        assertEquals("Clean Code", explicit.getBook_Name());
        assertEquals("Robert Martin", explicit.getBook_Author());
        assertEquals("9780134685991", explicit.getIsbn());
        assertEquals(1, explicit.getBookId());
        assertEquals(1, explicit.getBook_Id());
        assertEquals(2, explicit.getBook_Quantity());

        books legacy = new books(2, "Java", "Author", 3);
        assertTrue(books.validateISBN(legacy.getIsbn()));
    }

    @Test
    void addBookRejectsNullAndDuplicateCatalogId() {
        assertFalse(books.addBook(null));
        assertTrue(books.addBook(new books(10, "9780134685991", "First", "Author", 1)));
        assertFalse(books.addBook(new books(10, "9780133943030", "Second", "Author", 1)));
        assertEquals(1, books.totalAvailableCopies());
    }

    @Test
    void deprecatedCompatibilityAddMethodDelegatesToValidatedAdd() {
        books book = new books(16, "9780134685991", "Compatibility", "Author", 1);
        books.addBookWithoutDuplicateCheck(book);
        assertSame(book, books.searchBook("Compatibility"));
        assertEquals(1, books.totalAvailableCopies());
    }

    @Test
    void quantitySetterRejectsNegativeAndAcceptsZero() {
        books book = new books(11, "9780134685991", "Title", "Author", 2);
        assertThrows(IllegalArgumentException.class, () -> book.setBook_Quantity(-1));
        book.setBook_Quantity(0);
        assertEquals(0, book.getBook_Quantity());
    }

    @Test
    void borrowingRejectsInvalidMembersUnknownBooksAndUnavailableCopies() {
        LibraryTestFixture.addBook(12, "9780134685991", "One Copy", "Author", 1);

        assertFalse(books.issueBook(12, null));
        assertFalse(books.issueBook(12, "   "));
        assertFalse(books.issueBook(999, "Ali"));
        assertTrue(books.issueBook(12, "Ali"));
        assertFalse(books.issueBook(12, "Sara"));
        assertEquals(0, books.searchBook("One Copy").getBook_Quantity());
        assertEquals(1, books.getMemberBorrowedCount(" Ali "));
        assertEquals(0, books.getMemberBorrowedCount(null));
    }

    @Test
    void canBorrowReflectsInvalidAndValidMemberNamesAndLoanLimit() {
        assertFalse(books.canBorrow(null));
        assertFalse(books.canBorrow("  "));
        assertTrue(books.canBorrow("Ali"));
        assertEquals(5, books.getBorrowLimit());

        LibraryTestFixture.prepareBorrowingCatalog();
        for (int id = 501; id <= 505; id++) {
            assertTrue(books.issueBook(id, "Ali"));
        }
        assertFalse(books.canBorrow("Ali"));
    }

    @Test
    void returningRejectsInvalidMemberAndUnownedLoan() {
        LibraryTestFixture.addBook(13, "9780134685991", "Returnable", "Author", 1);
        assertFalse(books.returnBook(13, null));
        assertFalse(books.returnBook(13, "  "));
        assertFalse(books.returnBook(13, "Ali"));
        assertEquals(1, books.searchBook("Returnable").getBook_Quantity());
    }

    @Test
    void returnBookHandlesCatalogEntryRemovedAfterLoan() throws Exception {
        LibraryTestFixture.addBook(17, "9780133943030", "Removed Entry", "Author", 1);
        assertTrue(books.issueBook(17, "Ali"));

        // Exercise the defensive branch where a loan record remains but its catalog entry is gone.
        java.lang.reflect.Field catalog = books.class.getDeclaredField("libraryBooks");
        catalog.setAccessible(true);
        ((java.util.List<?>) catalog.get(null)).clear();

        assertFalse(books.returnBook(17, "Ali"));
    }

    @Test
    void borrowedCountIgnoresMalformedInternalLoanRecords() throws Exception {
        java.lang.reflect.Field recordsField = books.class.getDeclaredField("issuedBooks");
        recordsField.setAccessible(true);
        ((java.util.List<String>) recordsField.get(null)).add("malformed-record");

        assertEquals(0, books.getMemberBorrowedCount("Ali"));
    }

    @Test
    void searchReturnsNullWhenNoTitleMatches() {
        LibraryTestFixture.addBook(18, "9780133943030", "Known Title", "Author", 1);
        assertNull(books.searchBook("Missing Title"));
    }

    @Test
    void fineCalculationRejectsNegativeDaysAndHandlesAllRateBands() {
        assertThrows(IllegalArgumentException.class, () -> books.calculateFine(-1));
        assertEquals(0, books.calculateFine(0));
        assertEquals(60, books.calculateFine(3));
        assertEquals(200, books.calculateFine(4));
        assertEquals(350, books.calculateFine(7));
        assertEquals(800, books.calculateFine(8));
    }

    @Test
    void displayBooksPrintsNothingForEmptyCatalogAndDetailsForBooks() {
        PrintStream original = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));
            books.displayBooks();
            assertEquals("", captured.toString(StandardCharsets.UTF_8));

            LibraryTestFixture.addBook(14, "9780134685991", "Printed Title", "Printed Author", 2);
            books.displayBooks();
            String output = captured.toString(StandardCharsets.UTF_8);
            assertTrue(output.contains("Printed Title"));
            assertTrue(output.contains("Printed Author"));
            assertTrue(output.contains("Quantity: 2"));
        } finally {
            System.setOut(original);
        }
    }

    @Test
    void exportCatalogValidatesArgumentsAndWritesActualFile() throws Exception {
        CatalogWriter writer = (path, content) -> { };
        assertThrows(IllegalArgumentException.class, () -> books.exportCatalog(null, writer));
        assertThrows(IllegalArgumentException.class, () -> books.exportCatalog("  ", writer));
        assertThrows(IllegalArgumentException.class, () -> books.exportCatalog("catalog.txt", null));

        LibraryTestFixture.addBook(15, "9780134685991", "Exported Title", "Export Author", 4);
        Path output = tempDir.resolve("catalog.txt");
        books.exportCatalog(output.toString());
        String contents = Files.readString(output);
        assertTrue(contents.contains("Exported Title"));
        assertTrue(contents.contains("Export Author"));
        assertTrue(contents.contains("Quantity: 4"));
    }
}
