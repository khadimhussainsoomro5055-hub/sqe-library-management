package edu.sqe.libraryhub;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.jupiter.api.Test;

class BookSchemaApiTest extends ApiTestBase {
    @Test
    void bookListMatchesExactApiSchema() {
        addBook("9780132350884", 2);
        addBook("9780132350885", 1);

        given().when().get("/books")
        .then().statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/book-list.schema.json"));
    }
}
