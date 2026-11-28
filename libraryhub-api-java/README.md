Lab 12 implements the LibraryHub REST API as a separate Spring Boot module. The module keeps the existing console application unchanged and provides endpoints for adding, listing and finding books, borrowing and returning books, and checking a member's fine tier.

The API includes JSON error responses and is tested with JUnit 5 and REST Assured. The test suite covers successful requests, invalid input, boundary conditions, response structure and response time. GitHub Actions runs the Maven test suite.

To run the API locally, open a terminal in `libraryhub-api-java` and run `mvn spring-boot:run`. The service starts at `http://localhost:8080`. In another terminal, use the request examples in `docs/api-exploration.md` or import the requests into Postman.

Run the automated tests with `mvn test`.

The API response for a book exposes `title`, `isbn`, `author` and `availableCopies`. The internal `totalCopies` value is not included in the JSON response.