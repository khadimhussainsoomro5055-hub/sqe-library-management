package edu.sqe.libraryhub;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LibraryController {
    private final LibraryService service;

    public LibraryController(LibraryService service) {
        this.service = service;
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Map<String, Object> body) {
        String title = text(body, "title");
        String isbn = text(body, "isbn");
        String author = text(body, "author");
        Object copies = body.get("totalCopies");

        if (!(copies instanceof Integer count) || count < 1) {
            throw new ApiException(HttpStatus.BAD_REQUEST,
                    "totalCopies must be an integer >= 1");
        }

        return service.addBook(new Book(isbn, title, author, count));
    }

    @GetMapping("/books")
    public List<Book> allBooks() {
        return service.allBooks();
    }

    @GetMapping("/books/{isbn}")
    public Book getBook(@PathVariable String isbn) {
        return service.getBook(isbn);
    }

    @PostMapping("/members/{id}/borrow")
    public Map<String, Object> borrow(@PathVariable int id,
                                      @RequestBody Map<String, String> body) {
        String isbn = body.get("isbn");
        if (isbn == null || isbn.isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "isbn is required");
        }

        Book book = service.borrow(id, isbn);
        return Map.of("isbn", book.getIsbn(),
                "availableCopies", book.getAvailableCopies());
    }

    @PostMapping("/members/{id}/return")
    public Map<String, Object> giveBack(@PathVariable int id,
                                        @RequestBody Map<String, String> body) {
        String isbn = body.get("isbn");
        if (isbn == null || isbn.isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "isbn is required");
        }

        Book book = service.giveBack(id, isbn);
        return Map.of("isbn", book.getIsbn(),
                "availableCopies", book.getAvailableCopies());
    }

    @GetMapping("/members/{id}/fines")
    public Map<String, Object> fines(@PathVariable int id,
                                     @RequestParam int daysOverdue) {
        String tier;
        try {
            tier = LibraryService.fineTier(daysOverdue);
        } catch (IllegalArgumentException exception) {
            throw new ApiException(HttpStatus.BAD_REQUEST, exception.getMessage());
        }

        return Map.of("memberId", id,
                "daysOverdue", daysOverdue,
                "tier", tier);
    }

    private static String text(Map<String, Object> body, String key) {
        Object value = body.get(key);
        if (!(value instanceof String text) || text.isBlank()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, key + " is required");
        }
        return text;
    }
}
