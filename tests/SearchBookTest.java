import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SearchBookTest {
    @BeforeEach void setUp() {
        LibraryTestFixture.resetLibrary();
        LibraryTestFixture.addBook(901, "The Great Gatsby", "F. Scott Fitzgerald", 2);
    }
    @Test void searchIgnoresCapitalizationAndSurroundingSpaces() {
        assertSame(books.searchBook("The Great Gatsby"), books.searchBook("  the great gatsby  "));
    }
    @Test void nullOrBlankSearchReturnsNoResult() {
        assertNull(books.searchBook(null));
        assertNull(books.searchBook("   "));
    }
}
