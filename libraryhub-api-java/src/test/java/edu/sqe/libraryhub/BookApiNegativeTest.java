package edu.sqe.libraryhub;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.junit.jupiter.api.Test;

class BookApiNegativeTest extends ApiTestBase {
    @Test
    void N1_unknownIsbnReturnsNotFound() {
        given().when().get("/books/0000000000000")
        .then().statusCode(404)
            .contentType(JSON)
            .body("error", containsString("Unknown ISBN"));
    }

    @Test
    void N2_borrowingBookWithNoCopiesReturnsBadRequest() {
        addBook("9780132350884", 1);
        given().contentType(JSON).body(Map.of("isbn", "9780132350884"))
            .post("/members/501/borrow").then().statusCode(200);

        given().contentType(JSON).body(Map.of("isbn", "9780132350884"))
        .when().post("/members/502/borrow")
        .then().statusCode(400)
            .contentType(JSON)
            .body("error", equalTo("No copies available"));
    }

    @Test
    void N3_missingTitleReturnsBadRequest() {
        given().contentType(JSON)
            .body(Map.of("isbn", "9780132350884", "author", "R. Martin",
                    "totalCopies", 1))
        .when().post("/books")
        .then().statusCode(400)
            .contentType(JSON)
            .body("error", containsString("title"));
    }

    @Test
    void N4_zeroCopiesRejectedAndOneCopyAccepted() {
        given().contentType(JSON)
            .body(Map.of("title", "Zero", "isbn", "9780132350884",
                    "author", "Author", "totalCopies", 0))
        .when().post("/books")
        .then().statusCode(400);

        given().contentType(JSON)
            .body(Map.of("title", "One", "isbn", "9780132350885",
                    "author", "Author", "totalCopies", 1))
        .when().post("/books")
        .then().statusCode(201);
    }

    @Test
    void N5_duplicateIsbnReturnsConflict() {
        addBook("9780132350884", 1);

        given().contentType(JSON)
            .body(Map.of("title", "Duplicate", "isbn", "9780132350884",
                    "author", "Other", "totalCopies", 1))
        .when().post("/books")
        .then().statusCode(409)
            .contentType(JSON)
            .body("error", equalTo("ISBN already exists"));
    }

    @Test
    void N6_memberCannotBorrowSixthBook() {
        for (int i = 1; i <= 6; i++) {
            String isbn = String.format("9780132350%03d", i);
            addBook(isbn, 1);
            int status = given().contentType(JSON)
                .body(Map.of("isbn", isbn))
                .post("/members/501/borrow")
                .statusCode();
            if (i <= 5) {
                org.junit.jupiter.api.Assertions.assertEquals(200, status);
            } else {
                org.junit.jupiter.api.Assertions.assertEquals(400, status);
            }
        }
    }

    @Test
    void N7_returningBookNotBorrowedByMemberReturnsBadRequest() {
        addBook("9780132350884", 1);

        given().contentType(JSON).body(Map.of("isbn", "9780132350884"))
        .when().post("/members/501/return")
        .then().statusCode(400)
            .contentType(JSON)
            .body("error", equalTo("Book is not on loan to this member"));
    }

    @Test
    void missingBorrowIsbnReturnsBadRequest() {
        given().contentType(JSON).body(Map.of("other", "value"))
        .when().post("/members/501/borrow")
        .then().statusCode(400)
            .contentType(JSON)
            .body("error", equalTo("isbn is required"));
    }
}
