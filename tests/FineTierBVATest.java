import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

public class FineTierBVATest {

    @ParameterizedTest
    @CsvSource({
        "0, 'None'",
        "1, 'Low'",
        "6, 'Low'",
        "7, 'Low'",
        "8, 'Medium'",
        "9, 'Medium'",
        "13, 'Medium'",
        "14, 'Medium'",
        "15, 'High'",
        "16, 'High'",
        "29, 'High'",
        "30, 'High'",
        "31, 'Severe'",
        "32, 'Severe'"
    })
    void testFineTierBoundaries(int days, String expected) {
        assertEquals(
            expected,
            books.fineTier(days)
        );
    }

    @Test
    void testFineTierNegativeBoundary() {
        assertThrows(
            IllegalArgumentException.class,
            () -> books.fineTier(-1)
        );
    }
}