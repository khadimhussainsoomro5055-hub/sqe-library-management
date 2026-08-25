import java.util.ArrayList;
import java.util.List;

public class books {

    private int book_Id;
    private String book_Name;
    private String book_Author;
    private int book_Quantity;

    private static List<books> libraryBooks = new ArrayList<>();
    private static List<String> issuedBooks = new ArrayList<>();

    public books(int book_Id, String book_Name, String book_Author, int book_Quantity) {
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
        this.book_Quantity = book_Quantity;
    }

    public static void addBook(books book) {
        libraryBooks.add(book);
    }

    public static void addBookWithoutDuplicateCheck(books book) {
        libraryBooks.add(book);
    }

    public static boolean issueBook(int bookId, String memberName) {

    for (books book : libraryBooks) {

        if (book.getBook_Id() == bookId) {

            if (book.getBook_Quantity() <= 0) {
                return false;
            }

            book.setBook_Quantity(book.getBook_Quantity() - 1);
            issuedBooks.add(bookId + ":" + memberName);

            return true;
        }
    }

    return false;
}

    public static boolean returnBook(int bookId, String memberName) {

        String record = bookId + ":" + memberName;

        if (issuedBooks.contains(record)) {

            issuedBooks.remove(record);

            return true;
        }

        return false;
    }

    public static books searchBook(String searchName) {

        for (books book : libraryBooks) {

            if (book.getBook_Name().equals(searchName)) {
                return book;
            }
        }

        return null;
    }

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
}