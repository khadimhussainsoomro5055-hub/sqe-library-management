import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BorrowLimitTest {

    @BeforeEach
    void setUp() {
        LibraryTestFixture.prepareBorrowingCatalog();
    }

    static Stream<Arguments> borrowingCases() {
        return Stream.of(
            Arguments.of(0, true, "zero books"),
            Arguments.of(1, true, "one book"),
            Arguments.of(2, true, "two books"),
            Arguments.of(3, true, "three books"),
            Arguments.of(4, true, "four books"),
            Arguments.of(5, false, "five books")
        );
    }

    @ParameterizedTest(name = "Borrowing with {2} already borrowed")
    @MethodSource("borrowingCases")
    void testBorrowingLimit(int currentBorrowed, boolean expected, String testCase) {

        for (int i = 0; i < currentBorrowed; i++) {
            assertTrue(books.issueBook(501 + i, "Ali"));
        }

        boolean result = books.issueBook(501 + currentBorrowed, "Ali");

        assertEquals(expected, result);
    }
}