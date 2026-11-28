package books_management;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class books {

    private final int bookId;
    private final String isbn;
    private final String book_Name;
    private final String book_Author;
    private int book_Quantity;

    private static final List<books> libraryBooks = new ArrayList<>();
    private static final List<String> issuedBooks = new ArrayList<>();
    private static final Map<Integer, Integer> borrowingCounts = new HashMap<>();

    private static final int MAX_BORROW_LIMIT = 5;
    private static final int FINE_RATE_FIRST_TIER = 20;
    private static final int FINE_RATE_SECOND_TIER = 50;
    private static final int FINE_RATE_THIRD_TIER = 100;
    private static final int FIRST_FINE_TIER_MAX_DAYS = 3;
    private static final int SECOND_FINE_TIER_MAX_DAYS = 7;
    private static final int LOW_FINE_MAX_DAYS = 7;
    private static final int MEDIUM_FINE_MAX_DAYS = 14;
    private static final int HIGH_FINE_MAX_DAYS = 30;
    private static final String ISBN_PATTERN = "\\d{13}";
    private static final String BORROW_RECORD_SEPARATOR = ":";

    /**
     * Creates a book with an explicit ISBN. The internal book ID remains a
     * separate identifier so ISBN formatting is never confused with catalog IDs.
     */
    public books(int bookId, String isbn, String bookName,
                 String bookAuthor, int bookQuantity) {
        if (bookId < 0) {
            throw new IllegalArgumentException("Book ID cannot be negative.");
        }
        if (!validateISBN(isbn)) {
            throw new IllegalArgumentException(
                    "ISBN must contain exactly 13 numeric digits."
            );
        }
        if (bookName == null || bookName.trim().isEmpty()) {
            throw new IllegalArgumentException("Book name cannot be empty.");
        }
        if (bookAuthor == null || bookAuthor.trim().isEmpty()) {
            throw new IllegalArgumentException("Book author cannot be empty.");
        }
        if (bookQuantity < 0) {
            throw new IllegalArgumentException("Book quantity cannot be negative.");
        }

        this.bookId = bookId;
        this.isbn = isbn;
        this.book_Name = bookName.trim();
        this.book_Author = bookAuthor.trim();
        this.book_Quantity = bookQuantity;
    }

    /**
     * Backward-compatible constructor for the original lab catalog examples.
     * It derives a unique 13-digit placeholder ISBN from the numeric catalog ID.
     * New code should use the constructor that accepts an explicit ISBN.
     */
    @Deprecated
    public books(int bookId, String bookName, String bookAuthor, int bookQuantity) {
        this(bookId, String.format("%013d", 9780000000000L + bookId),
                bookName, bookAuthor, bookQuantity);
    }

    public int getBookId() { return bookId; }

    /** @deprecated use getBookId() */
    @Deprecated
    public int getBook_Id() { return getBookId(); }

    public String getIsbn() { return isbn; }
    public String getBook_Name() { return book_Name; }
    public String getBook_Author() { return book_Author; }
    public int getBook_Quantity() { return book_Quantity; }

    public void setBook_Quantity(int bookQuantity) {
        if (bookQuantity < 0) {
            throw new IllegalArgumentException("Book quantity cannot be negative.");
        }
        this.book_Quantity = bookQuantity;
    }

    public static boolean addBook(books book) {
        if (book == null || !validateISBN(book.getIsbn())) {
            return false;
        }
        for (books existingBook : libraryBooks) {
            if (existingBook.getBookId() == book.getBookId()
                    || existingBook.getIsbn().equals(book.getIsbn())) {
                return false;
            }
        }
        libraryBooks.add(book);
        return true;
    }

    /** Kept for compatibility with old lab examples; validation still applies. */
    @Deprecated
    public static void addBookWithoutDuplicateCheck(books book) {
        addBook(book);
    }

    public static boolean issueBook(int bookId, String memberName) {
        if (memberName == null || memberName.trim().isEmpty()) {
            return false;
        }
        String normalizedMember = memberName.trim();
        if (getMemberBorrowedCount(normalizedMember) >= MAX_BORROW_LIMIT) {
            return false;
        }
        String record = bookId + BORROW_RECORD_SEPARATOR + normalizedMember;
        if (issuedBooks.contains(record)) {
            return false;
        }

        for (books book : libraryBooks) {
            if (book.getBookId() == bookId) {
                if (book.getBook_Quantity() <= 0) {
                    return false;
                }
                book.setBook_Quantity(book.getBook_Quantity() - 1);
                issuedBooks.add(record);
                borrowingCounts.put(bookId,
                        borrowingCounts.getOrDefault(bookId, 0) + 1);
                return true;
            }
        }
        return false;
    }

    public static boolean returnBook(int bookId, String memberName) {
        if (memberName == null || memberName.trim().isEmpty()) {
            return false;
        }
        String record = bookId + BORROW_RECORD_SEPARATOR + memberName.trim();
        int recordIndex = issuedBooks.indexOf(record);
        if (recordIndex < 0) {
            return false;
        }

        books matchingBook = null;
        for (books book : libraryBooks) {
            if (book.getBookId() == bookId) {
                matchingBook = book;
                break;
            }
        }
        if (matchingBook == null) {
            return false;
        }

        issuedBooks.remove(recordIndex);
        matchingBook.setBook_Quantity(matchingBook.getBook_Quantity() + 1);
        return true;
    }

    public static books searchBook(String searchName) {
        if (searchName == null) {
            return null;
        }
        String normalizedSearch = searchName.trim();
        if (normalizedSearch.isEmpty()) {
            return null;
        }
        for (books book : libraryBooks) {
            if (book.getBook_Name().equalsIgnoreCase(normalizedSearch)) {
                return book;
            }
        }
        return null;
    }

    public static int getMemberBorrowedCount(String memberName) {
        if (memberName == null) {
            return 0;
        }
        int count = 0;
        for (String record : issuedBooks) {
            String[] parts = record.split(BORROW_RECORD_SEPARATOR, 2);
            if (parts.length == 2 && parts[1].equals(memberName.trim())) {
                count++;
            }
        }
        return count;
    }

    public static boolean canBorrow(String memberName) {
        return memberName != null
                && !memberName.trim().isEmpty()
                && getMemberBorrowedCount(memberName) < MAX_BORROW_LIMIT;
    }

    public static int getBorrowLimit() { return MAX_BORROW_LIMIT; }

    public static int calculateFine(int overdueDays) {
        if (overdueDays < 0) {
            throw new IllegalArgumentException("Overdue days cannot be negative.");
        }
        if (overdueDays == 0) {
            return 0;
        }
        if (overdueDays <= FIRST_FINE_TIER_MAX_DAYS) {
            return overdueDays * FINE_RATE_FIRST_TIER;
        }
        if (overdueDays <= SECOND_FINE_TIER_MAX_DAYS) {
            return overdueDays * FINE_RATE_SECOND_TIER;
        }
        return overdueDays * FINE_RATE_THIRD_TIER;
    }

    public static String fineTier(int overdueDays) {
        if (overdueDays < 0) {
            throw new IllegalArgumentException("Overdue days cannot be negative.");
        }
        if (overdueDays == 0) return "None";
        if (overdueDays <= LOW_FINE_MAX_DAYS) return "Low";
        if (overdueDays <= MEDIUM_FINE_MAX_DAYS) return "Medium";
        if (overdueDays <= HIGH_FINE_MAX_DAYS) return "High";
        return "Severe";
    }

    public static boolean validateISBN(String isbn) {
        return isbn != null && isbn.matches(ISBN_PATTERN);
    }

    private static String formatBookDetails(books book) {
        return "ID: " + book.getBookId()
                + ", ISBN: " + book.getIsbn()
                + ", Name: " + book.getBook_Name()
                + ", Author: " + book.getBook_Author()
                + ", Quantity: " + book.getBook_Quantity();
    }

    public static void displayBooks() {
        for (books book : libraryBooks) {
            System.out.println(formatBookDetails(book));
        }
    }

    public static int totalAvailableCopies() {
        int total = 0;
        for (books book : libraryBooks) {
            total += book.getBook_Quantity();
        }
        return total;
    }

    public static List<books> rankBooksByPopularity() {
        List<books> rankedBooks = new ArrayList<>(libraryBooks);
        rankedBooks.sort(
                Comparator.comparingInt(
                        (books book) -> borrowingCounts.getOrDefault(book.getBookId(), 0)
                ).reversed().thenComparing(
                        books::getBook_Name, String.CASE_INSENSITIVE_ORDER
                )
        );
        return rankedBooks;
    }

    public static void exportCatalog(String path, CatalogWriter writer) {
        if (path == null || path.trim().isEmpty() || writer == null) {
            throw new IllegalArgumentException("Path and writer are required.");
        }
        StringBuilder content = new StringBuilder();
        for (books book : libraryBooks) {
            content.append(formatBookDetails(book)).append(System.lineSeparator());
        }
        try {
            writer.write(path, content.toString());
        } catch (IOException e) {
            throw new LibraryIOError("Failed to export library catalog.", e);
        }
    }

    public static void clearLibrary() {
        libraryBooks.clear();
        issuedBooks.clear();
        borrowingCounts.clear();
    }

    public static void exportCatalog(String path) {
        exportCatalog(path, (filePath, content) ->
                Files.writeString(Path.of(filePath), content));
    }
}
