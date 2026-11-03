
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ExportCatalogTest {

    @BeforeEach
    void setUp() {
        LibraryTestFixture.resetLibrary();
    }

    // Test 1: Successful export writes the expected catalog content.
    @Test
    void testExportCatalogSuccess() {
        // Arrange
        LibraryTestFixture.addBook(
            301, "Clean Code", "Robert Martin", 4
        );

        StringBuilder writtenContent = new StringBuilder();

        CatalogWriter fakeWriter = (path, content) -> {
            assertEquals("catalog.txt", path);
            writtenContent.append(content);
        };

        // Act
        books.exportCatalog("catalog.txt", fakeWriter);

        // Assert
        String expected =
            "ID: 301, Name: Clean Code, Author: Robert Martin, Quantity: 4"
            + System.lineSeparator();

        assertEquals(expected, writtenContent.toString());
    }

    // Test 2: An I/O failure becomes a clear LibraryIOError.
    @Test
    void testExportCatalogHandlesIOException() {
        // Arrange
        LibraryTestFixture.addBook(
            302, "Java Programming", "James Gosling", 2
        );

        CatalogWriter failingWriter = (path, content) -> {
            throw new IOException("Simulated disk failure");
        };

        // Act and Assert
        LibraryIOError exception = assertThrows(
            LibraryIOError.class,
            () -> books.exportCatalog("catalog.txt", failingWriter)
        );

        assertTrue(
            exception.getMessage().contains("Failed to export")
        );

        assertTrue(
            exception.getCause() instanceof IOException
        );
    }
}
