import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ISBNValidationTest {

    @Test
    void testValidISBN() {
        assertTrue(
            books.validateISBN("9780134685991")
        );
    }

    @Test
    void testEmptyISBN() {
        assertFalse(
            books.validateISBN("")
        );
    }

    @Test
    void testTooShortISBN() {
        assertFalse(
            books.validateISBN("123456789")
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "97801346ABC91",
        "978-0134685991",
        "978013468599!"
    })
    void testISBNWithLettersOrSymbols(String isbn) {
        assertFalse(
            books.validateISBN(isbn)
        );
    }
}