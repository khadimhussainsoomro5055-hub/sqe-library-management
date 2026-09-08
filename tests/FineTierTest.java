import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class FineTierTest {

    @ParameterizedTest
    @CsvSource({
        "0, 'None'",
        "4, 'Low'",
        "10, 'Medium'",
        "20, 'High'",
        "45, 'Severe'"
    })
    void testFineTierValidClasses(int days, String expected) {
        assertEquals(expected, books.fineTier(days));
    }

    @Test
    void testFineTierNegativeDays() {
        assertThrows(
            IllegalArgumentException.class,
            () -> books.fineTier(-3)
        );
    }
}