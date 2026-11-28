package tests;

import books_management.books;

public final class LibraryTestFixture {

    private LibraryTestFixture() {
    }

    public static void resetLibrary() {
        books.clearLibrary();
    }

    public static void addBook(int id, String title, String author, int quantity) {
        boolean added = books.addBook(new books(id, title, author, quantity));
        if (!added) {
            throw new IllegalStateException("Fixture book could not be added: " + id);
        }
    }

    public static void addBook(int id, String isbn, String title, String author, int quantity) {
        boolean added = books.addBook(new books(id, isbn, title, author, quantity));
        if (!added) {
            throw new IllegalStateException("Fixture book could not be added: " + id);
        }
    }

    public static void prepareBorrowingCatalog() {
        resetLibrary();
        for (int i = 1; i <= 6; i++) {
            addBook(500 + i, "Book " + i, "Author " + i, 1);
        }
    }
}
