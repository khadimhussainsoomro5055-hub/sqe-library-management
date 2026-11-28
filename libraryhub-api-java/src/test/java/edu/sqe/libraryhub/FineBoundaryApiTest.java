package edu.sqe.libraryhub;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.Test;

class FineBoundaryApiTest extends ApiTestBase {
    @ParameterizedTest
    @CsvSource({
        "0,None", "1,Low", "7,Low", "8,Medium",
        "14,Medium", "15,High", "30,High", "31,Severe"
    })
    void N9_fineTierBoundaries(int days, String tier) {
        given().queryParam("daysOverdue", days)
        .when().get("/members/7/fines")
        .then().statusCode(200)
            .contentType(ContentType.JSON)
            .body("tier", equalTo(tier));
    }

    @Test
    void N9_negativeDaysReturnsBadRequest() {
        given().queryParam("daysOverdue", -1)
        .when().get("/members/7/fines")
        .then().statusCode(400)
            .contentType(ContentType.JSON)
            .body("error", equalTo("daysOverdue cannot be negative"));
    }

    @Test
    void missingDaysOverdueReturnsBadRequest() {
        given().when().get("/members/7/fines")
        .then().statusCode(400)
            .contentType(ContentType.JSON)
            .body("error", equalTo("Invalid or missing request value"));
    }
}
