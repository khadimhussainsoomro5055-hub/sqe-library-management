package edu.sqe.libraryhub;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;

import java.util.Map;

import org.junit.jupiter.api.Test;

class BookApiPositiveTest extends ApiTestBase {
    @Test
    void addBookReturnsCreatedBookContract() {
        given().contentType(JSON)
            .body(Map.of("title", "Clean Code", "isbn", "9780132350884",
                    "author", "R. Martin", "totalCopies", 2))
        .when().post("/books")
        .then()
            .statusCode(201)
            .contentType(JSON)
            .body("title", equalTo("Clean Code"))
            .body("isbn", equalTo("9780132350884"))
            .body("author", equalTo("R. Martin"))
            .body("availableCopies", equalTo(2));
    }

    @Test
    void listBooksReturnsArrayOfBooks() {
        addBook("9780132350884", 2);
        addBook("9780132350885", 1);

        given().when().get("/books")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("$", hasSize(2))
            .body("[0].title", equalTo("Clean Code"))
            .body("[1].isbn", equalTo("9780132350885"));
    }

    @Test
    void getBookByIsbnReturnsMatchingBook() {
        addBook("9780132350884", 3);

        given().when().get("/books/9780132350884")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("isbn", equalTo("9780132350884"))
            .body("title", equalTo("Clean Code"))
            .body("availableCopies", equalTo(3));
    }

    @Test
    void borrowBookWhenAvailable() {
        addBook("9780132350884", 1);

        given().contentType(JSON).body(Map.of("isbn", "9780132350884"))
        .when().post("/members/501/borrow")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("isbn", equalTo("9780132350884"))
            .body("availableCopies", equalTo(0));
    }

    @Test
    void returnBookRestoresAvailableCopy() {
        addBook("9780132350884", 1);
        given().contentType(JSON).body(Map.of("isbn", "9780132350884"))
            .post("/members/501/borrow").then().statusCode(200);

        given().contentType(JSON).body(Map.of("isbn", "9780132350884"))
        .when().post("/members/501/return")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("isbn", equalTo("9780132350884"))
            .body("availableCopies", equalTo(1));
    }

    @Test
    void getFineTierForMember() {
        given().queryParam("daysOverdue", 15)
        .when().get("/members/501/fines")
        .then()
            .statusCode(200)
            .contentType(JSON)
            .body("memberId", equalTo(501))
            .body("daysOverdue", equalTo(15))
            .body("tier", equalTo("High"));
    }

    @Test
    void bookListRespondsWithinOneSecond() {
        given().when().get("/books")
        .then()
            .statusCode(200)
            .time(lessThan(1000L));
        // Shared CI machines can be noisy; the 1000 ms limit is intentionally generous.
    }
}
