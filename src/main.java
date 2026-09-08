public class main {

    public static void main(String[] args) {

        System.out.println("=== Library Management System ===");

        // Start with an empty library
        books.clearLibrary();

        // 1. Add books
        books book1 = new books(
            101,
            "Java Programming",
            "James Gosling",
            5
        );

        books book2 = new books(
            102,
            "Software Engineering",
            "Ian Sommerville",
            2
        );

        System.out.println("\n--- Adding Books ---");

        System.out.println(
            "Book 101 added: " + books.addBook(book1)
        );

        System.out.println(
            "Book 102 added: " + books.addBook(book2)
        );

        // 2. Try duplicate ID
        books duplicateBook = new books(
            101,
            "Clean Code",
            "Robert Martin",
            3
        );

        System.out.println(
            "Duplicate book added: "
            + books.addBook(duplicateBook)
        );

        // 3. Display books
        System.out.println("\n--- Current Books ---");
        books.displayBooks();

        // 4. Issue a book
        System.out.println("\n--- Issue Book ---");

        boolean issued = books.issueBook(
            101,
            "Ali"
        );

        System.out.println(
            "Book 101 issued to Ali: " + issued
        );

        System.out.println(
            "Ali borrowed: "
            + books.getMemberBorrowedCount("Ali")
            + " book(s)"
        );

        // 5. Search book
        System.out.println("\n--- Search Book ---");

        books result = books.searchBook(
            "java programming"
        );

        if (result != null) {
            System.out.println(
                "Book found: "
                + result.getBook_Name()
            );
        } else {
            System.out.println("Book not found.");
        }

        // 6. Return book
        System.out.println("\n--- Return Book ---");

        boolean returned = books.returnBook(
            101,
            "Ali"
        );

        System.out.println(
            "Book 101 returned by Ali: " + returned
        );

        // 7. Fine calculation
        System.out.println("\n--- Fine Calculation ---");

        System.out.println(
            "Fine for 0 days: Rs. "
            + books.calculateFine(0)
        );

        System.out.println(
            "Fine for 5 days: Rs. "
            + books.calculateFine(5)
        );

        System.out.println(
            "Fine for 8 days: Rs. "
            + books.calculateFine(8)
        );

        // 8. Final book information
        System.out.println("\n--- Final Books ---");
        books.displayBooks();

        System.out.println("\n=== Program Finished ===");
    }
}
