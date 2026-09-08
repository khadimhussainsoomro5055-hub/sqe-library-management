import java.util.ArrayList;
import java.util.List;

public class books {

    private int book_Id;
    private String book_Name;
    private String book_Author;
    private int book_Quantity;

    private static List<books> libraryBooks = new ArrayList<>();
    private static List<String> issuedBooks = new ArrayList<>();

    // Maximum number of books one member can borrow
    private static final int MAX_BORROW_LIMIT = 5;

    // Fine rates
    private static final int FINE_RATE_FIRST_TIER = 20;
    private static final int FINE_RATE_SECOND_TIER = 50;
    private static final int FINE_RATE_THIRD_TIER = 100;

    public books(int book_Id, String book_Name, String book_Author, int book_Quantity) {

        if (book_Quantity < 0) {
            throw new IllegalArgumentException("Book quantity cannot be negative.");
        }

        this.book_Id = book_Id;
        this.book_Name = book_Name;
        this.book_Author = book_Author;
        this.book_Quantity = book_Quantity;
    }

    public int getBook_Id() {
        return book_Id;
    }

    public String getBook_Name() {
        return book_Name;
    }

    public String getBook_Author() {
        return book_Author;
    }

    public int getBook_Quantity() {
        return book_Quantity;
    }

    public void setBook_Quantity(int book_Quantity) {

        if (book_Quantity < 0) {
            throw new IllegalArgumentException(
                "Book quantity cannot be negative."
            );
        }

        this.book_Quantity = book_Quantity;
    }

    // Add a new book
    public static boolean addBook(books book) {

        if (book == null) {
            return false;
        }

        if (book.getBook_Quantity() < 0) {
            return false;
        }

        // Check for duplicate book ID
        for (books existingBook : libraryBooks) {

            if (existingBook.getBook_Id() == book.getBook_Id()) {
                return false;
            }
        }

        libraryBooks.add(book);
        return true;
    }

    // Kept for compatibility with old Lab 3 code
    public static void addBookWithoutDuplicateCheck(books book) {
        libraryBooks.add(book);
    }

    // Issue a book to a member
    public static boolean issueBook(int bookId, String memberName) {

        if (memberName == null || memberName.trim().isEmpty()) {
            return false;
        }

        // Check borrowing limit
        if (getMemberBorrowedCount(memberName) >= MAX_BORROW_LIMIT) {
            return false;
        }

        for (books book : libraryBooks) {

            if (book.getBook_Id() == bookId) {

                // No copies available
                if (book.getBook_Quantity() <= 0) {
                    return false;
                }

                // Issue book
                book.setBook_Quantity(
                    book.getBook_Quantity() - 1
                );

                issuedBooks.add(
                    bookId + ":" + memberName
                );

                return true;
            }
        }

        // Book does not exist
        return false;
    }

    // Return a book
    public static boolean returnBook(int bookId, String memberName) {

        String record = bookId + ":" + memberName;

        if (issuedBooks.contains(record)) {

            issuedBooks.remove(record);

            // Increase available quantity
            for (books book : libraryBooks) {

                if (book.getBook_Id() == bookId) {

                    book.setBook_Quantity(
                        book.getBook_Quantity() + 1
                    );

                    break;
                }
            }

            return true;
        }

        return false;
    }

    // Search book by name - case insensitive
    public static books searchBook(String searchName) {

        if (searchName == null) {
            return null;
        }

        for (books book : libraryBooks) {

            if (book.getBook_Name()
                    .equalsIgnoreCase(searchName.trim())) {

                return book;
            }
        }

        return null;
    }

    // Count books currently borrowed by a member
    public static int getMemberBorrowedCount(String memberName) {

        int count = 0;

        for (String record : issuedBooks) {

            String[] parts = record.split(":", 2);

            if (parts.length == 2 &&
                parts[1].equals(memberName)) {

                count++;
            }
        }

        return count;
    }

    // Check whether member can borrow another book
    public static boolean canBorrow(String memberName) {

        return getMemberBorrowedCount(memberName)
                < MAX_BORROW_LIMIT;
    }

    // Get maximum borrowing limit
    public static int getBorrowLimit() {
        return MAX_BORROW_LIMIT;
    }

    // Calculate fine based on overdue days
    public static int calculateFine(int overdueDays) {

        if (overdueDays <= 0) {
            return 0;
        }

        if (overdueDays <= 3) {
            return overdueDays * FINE_RATE_FIRST_TIER;
        }

        if (overdueDays <= 7) {
            return overdueDays * FINE_RATE_SECOND_TIER;
        }

        return overdueDays * FINE_RATE_THIRD_TIER;
    }

    // Return fine tier based on overdue days
    public static String fineTier(int overdueDays) {

        if (overdueDays < 0) {
            throw new IllegalArgumentException(
                "Overdue days cannot be negative."
            );
        }

        if (overdueDays == 0) {
            return "None";
        }

        if (overdueDays <= 7) {
            return "Low";
        }

        if (overdueDays <= 14) {
            return "Medium";
        }

        if (overdueDays <= 30) {
            return "High";
        }

        return "Severe";
    }

    // Validate ISBN - exactly 13 numeric digits
    public static boolean validateISBN(String isbn) {

        if (isbn == null) {
            return false;
        }

        return isbn.matches("\\d{13}");
    }

    // Display all books
    public static void displayBooks() {

        for (books book : libraryBooks) {

            System.out.println(
                "ID: " + book.getBook_Id()
                + ", Name: " + book.getBook_Name()
                + ", Author: " + book.getBook_Author()
                + ", Quantity: " + book.getBook_Quantity()
            );
        }
    }

    // Clear data - useful for testing
    public static void clearLibrary() {
        libraryBooks.clear();
        issuedBooks.clear();
    }
}