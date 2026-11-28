package edu.sqe.libraryhub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    public static final int MAX_LOANS = 5;

    private final Map<String, Book> books = new LinkedHashMap<>();
    private final Map<Integer, List<String>> loans = new HashMap<>();

    public synchronized Book addBook(Book book) {
        if (books.containsKey(book.getIsbn())) {
            throw new ApiException(HttpStatus.CONFLICT, "ISBN already exists");
        }
        books.put(book.getIsbn(), book);
        return book;
    }

    public synchronized List<Book> allBooks() {
        return new ArrayList<>(books.values());
    }

    public synchronized Book getBook(String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Unknown ISBN: " + isbn);
        }
        return book;
    }

    public synchronized Book borrow(int memberId, String isbn) {
        Book book = getBook(isbn);
        List<String> memberLoans = loans.computeIfAbsent(memberId, key -> new ArrayList<>());

        if (book.getAvailableCopies() == 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "No copies available");
        }
        if (memberLoans.size() >= MAX_LOANS) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Loan limit of 5 reached");
        }

        memberLoans.add(isbn);
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        return book;
    }

    public synchronized Book giveBack(int memberId, String isbn) {
        Book book = getBook(isbn);
        List<String> memberLoans = loans.getOrDefault(memberId, new ArrayList<>());

        if (!memberLoans.remove(isbn)) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "Book is not on loan to this member");
        }

        book.setAvailableCopies(book.getAvailableCopies() + 1);
        return book;
    }

    public static String fineTier(int daysOverdue) {
        if (daysOverdue < 0) {
            throw new IllegalArgumentException("daysOverdue cannot be negative");
        }
        if (daysOverdue == 0) return "None";
        if (daysOverdue <= 7) return "Low";
        if (daysOverdue <= 14) return "Medium";
        if (daysOverdue <= 30) return "High";
        return "Severe";
    }

    synchronized void reset() {
        books.clear();
        loans.clear();
    }
}
