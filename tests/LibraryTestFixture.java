
public class LibraryTestFixture {

    // Shared helper: resets mutable library state before preparing a test.
    public static void resetLibrary() {
        books.clearLibrary();
    }

    // Reusable helper: adds a book with the supplied details.
    public static void addBook(
            int id,
            String title,
            String author,
            int quantity) {

        books.addBook(
            new books(id, title, author, quantity)
        );
    }

    // Reusable helper: prepares a catalog of six books for borrowing tests.
    public static void prepareBorrowingCatalog() {
        resetLibrary();

        for (int i = 1; i <= 6; i++) {
            addBook(
                500 + i,
                "Book " + i,
                "Author " + i,
                1
            );
        }
    }
}
