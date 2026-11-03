
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TotalAvailableCopiesTest {

    @BeforeEach
    void setUp() {
        LibraryTestFixture.resetLibrary();
    }

    // Test 1: An empty catalog should return zero copies.
    @Test
    void testTotalAvailableCopiesEmptyCatalog() {
        // Act
        int total = books.totalAvailableCopies();

        // Assert
        assertEquals(0, total);
    }

    // Test 2: A catalog with one book returns its quantity.
    @Test
    void testTotalAvailableCopiesSingleBook() {
        // Arrange
        LibraryTestFixture.addBook(
            201, "Java Programming", "James Gosling", 5
        );

        // Act
        int total = books.totalAvailableCopies();

        // Assert
        assertEquals(5, total);
    }

    // Test 3: A catalog with multiple books returns their sum.
    @Test
    void testTotalAvailableCopiesMultipleBooks() {
        // Arrange
        LibraryTestFixture.addBook(
            201, "Java Programming", "James Gosling", 2
        );
        LibraryTestFixture.addBook(
            202, "Clean Code", "Robert Martin", 3
        );
        LibraryTestFixture.addBook(
            203, "Operating Systems", "Abraham Silberschatz", 5
        );

        // Act
        int total = books.totalAvailableCopies();

        // Assert
        assertEquals(10, total);
    }
}
