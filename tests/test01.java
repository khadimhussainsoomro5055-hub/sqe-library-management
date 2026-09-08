public class test01 {

    private static int passed = 0;
    private static int failed = 0;

    public static void main(String[] args) {

        System.out.println("=== Library Management System Tests ===");

        testAddValidBook();
        testDuplicateBookId();
        testNegativeQuantity();
        testIssueAvailableBook();
        testIssueUnavailableBook();
        testReturnBook();
        testInvalidReturn();
        testSearchBook();
        testBorrowingLimit();
        testFineCalculation();

        System.out.println("\n=== Test Summary ===");
        System.out.println("Passed: " + passed);
        System.out.println("Failed: " + failed);

        if (failed == 0) {
            System.out.println("All tests passed.");
        } else {
            System.out.println("Some tests failed.");
        }
    }

    private static void testAddValidBook() {

        books.clearLibrary();

        books book = new books(
            101,
            "Java Programming",
            "James Gosling",
            5
        );

        boolean result = books.addBook(book);

        check(
            "Add valid book",
            result == true
        );
    }

    // Regression test for Issue #5: Duplicate book IDs are allowed
    private static void testDuplicateBookId() {

        books.clearLibrary();

        books book1 = new books(
            101,
            "Java Programming",
            "James Gosling",
            5
        );

        books book2 = new books(
            101,
            "Clean Code",
            "Robert Martin",
            3
        );

        books.addBook(book1);

        boolean result = books.addBook(book2);

        check(
            "Reject duplicate book ID",
            result == false
        );
    }

    // Regression test for Issue #4: Negative book quantity is accepted
    private static void testNegativeQuantity() {

        books.clearLibrary();

        boolean rejected = false;

        try {

            books book = new books(
                101,
                "Java Programming",
                "James Gosling",
                -5
            );

            books.addBook(book);

        } catch (IllegalArgumentException e) {

            rejected = true;
        }

        check(
            "Reject negative quantity",
            rejected
        );
    }

    private static void testIssueAvailableBook() {

        books.clearLibrary();

        books book = new books(
            101,
            "Java Programming",
            "James Gosling",
            3
        );

        books.addBook(book);

        boolean result = books.issueBook(
            101,
            "Ali"
        );

        check(
            "Issue available book",
            result && book.getBook_Quantity() == 2
        );
    }

    private static void testIssueUnavailableBook() {

        books.clearLibrary();

        books book = new books(
            102,
            "Software Engineering",
            "Ian Sommerville",
            0
        );

        books.addBook(book);

        boolean result = books.issueBook(
            102,
            "Ali"
        );

        check(
            "Reject unavailable book",
            result == false &&
            book.getBook_Quantity() == 0
        );
    }

    private static void testReturnBook() {

        books.clearLibrary();

        books book = new books(
            103,
            "Operating Systems",
            "Abraham Silberschatz",
            1
        );

        books.addBook(book);

        books.issueBook(
            103,
            "Ahmed"
        );

        boolean result = books.returnBook(
            103,
            "Ahmed"
        );

        check(
            "Return issued book",
            result &&
            book.getBook_Quantity() == 1
        );
    }

    private static void testInvalidReturn() {

        books.clearLibrary();

        books book = new books(
            104,
            "Database Systems",
            "Elmasri",
            2
        );

        books.addBook(book);

        boolean result = books.returnBook(
            104,
            "Ali"
        );

        check(
            "Reject invalid return",
            result == false
        );
    }

    private static void testSearchBook() {

        books.clearLibrary();

        books book = new books(
            105,
            "Database Systems",
            "Elmasri",
            3
        );

        books.addBook(book);

        books result = books.searchBook(
            "database systems"
        );

        check(
            "Case-insensitive book search",
            result != null &&
            result.getBook_Id() == 105
        );
    }

    private static void testBorrowingLimit() {

        books.clearLibrary();

        books book1 = new books(
            201,
            "Book One",
            "Author One",
            2
        );

        books book2 = new books(
            202,
            "Book Two",
            "Author Two",
            2
        );

        books book3 = new books(
            203,
            "Book Three",
            "Author Three",
            2
        );

        books book4 = new books(
            204,
            "Book Four",
            "Author Four",
            2
        );

        books.addBook(book1);
        books.addBook(book2);
        books.addBook(book3);
        books.addBook(book4);

        boolean first = books.issueBook(201, "Ali");
        boolean second = books.issueBook(202, "Ali");
        boolean third = books.issueBook(203, "Ali");
        boolean fourth = books.issueBook(204, "Ali");

        check(
            "Enforce member borrowing limit",
            first &&
            second &&
            third &&
            !fourth
        );
    }

    private static void testFineCalculation() {

        books.clearLibrary();

        int zeroDays = books.calculateFine(0);
        int fiveDays = books.calculateFine(5);
        int eightDays = books.calculateFine(8);

        check(
            "Calculate fines",
            zeroDays == 0 &&
            fiveDays > 0 &&
            eightDays > fiveDays
        );
    }

    private static void check(
        String testName,
        boolean condition
    ) {

        if (condition) {

            System.out.println(
                "[PASS] " + testName
            );

            passed++;

        } else {

            System.out.println(
                "[FAIL] " + testName
            );

            failed++;
        }
    }
}