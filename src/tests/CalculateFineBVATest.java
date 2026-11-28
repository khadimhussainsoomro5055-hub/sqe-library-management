package tests;

import books_management.books;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculateFineBVATest {
    @ParameterizedTest
    @CsvSource({
        "-1, INVALID", "0, 0", "1, 20", "2, 40", "3, 60",
        "4, 200", "5, 250", "6, 300", "7, 350", "8, 800", "9, 900"
    })
    void calculatesFineAtEveryRateBoundary(int days, String expected) {
        if ("INVALID".equals(expected)) {
            assertThrows(IllegalArgumentException.class, () -> books.calculateFine(days));
        } else {
            assertEquals(Integer.parseInt(expected), books.calculateFine(days));
        }
    }
}
