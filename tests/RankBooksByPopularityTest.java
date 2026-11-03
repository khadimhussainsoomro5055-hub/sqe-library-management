import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RankBooksByPopularityTest {

    @BeforeEach
    void setUp() {
        LibraryTestFixture.resetLibrary();
    }

    @Test
    void testRankBooksEmptyCatalogReturnsEmptyList() {

        List<books> result = books.rankBooksByPopularity();

        assertEquals(0, result.size());
    }
}