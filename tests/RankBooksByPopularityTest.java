
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

    @Test
    void testRankBooksSingleBookReturnsThatBook() {

        LibraryTestFixture.addBook(
                201, "Java Programming", "James Gosling", 5
        );

        List<books> result = books.rankBooksByPopularity();

        assertEquals(1, result.size());
        assertEquals(
                "Java Programming",
                result.get(0).getBook_Name()
        );
    }

    @Test
    void testRankBooksByPopularityDescending() {

        // Add books in an order different from their popularity.
        LibraryTestFixture.addBook(
                303, "Python Basics", "John Smith", 5
        );

        LibraryTestFixture.addBook(
                301, "Java Programming", "James Gosling", 5
        );

        LibraryTestFixture.addBook(
                302, "Clean Code", "Robert Martin", 5
        );

        // Java Programming: 3 borrowings
        books.issueBook(301, "Ali");
        books.issueBook(301, "Ahmed");
        books.issueBook(301, "Sara");

        // Clean Code: 2 borrowings
        books.issueBook(302, "Ali");
        books.issueBook(302, "Ahmed");

        // Python Basics: 1 borrowing
        books.issueBook(303, "Ali");

        List<books> result = books.rankBooksByPopularity();

        assertEquals(3, result.size());

        assertEquals(
                "Java Programming",
                result.get(0).getBook_Name()
        );

        assertEquals(
                "Clean Code",
                result.get(1).getBook_Name()
        );

        assertEquals(
                "Python Basics",
                result.get(2).getBook_Name()
        );
    }
}
