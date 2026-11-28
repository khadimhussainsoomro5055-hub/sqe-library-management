# LibraryHub API — Manual Exploration Log

**Environment:** Spring Boot 3.x, Java 17+, localhost:8080  
**Purpose:** Perform the manual exploration required by Lab 12 before relying on the automated REST Assured tests.

## Start the API

From the `libraryhub-api-java` directory:

```bash
mvn spring-boot:run
```

Keep the application terminal open. Run the requests below in a second terminal or import them into Postman.

## Request and response record

The response examples below are the API contract examples derived from the implementation. Replace the **Actual response** column with the response captured from your own local run before final submission. Do not report these examples as personally captured terminal output.

| # | Method and path | Request body / query | Expected status | Expected response |
|---|---|---|---:|---|
| 1 | POST /books | `{"title":"Clean Code","isbn":"9780132350884","author":"R. Martin","totalCopies":1}` | 201 | Book JSON with title, isbn, author and availableCopies=1 |
| 2 | GET /books | None | 200 | JSON array containing the created book |
| 3 | GET /books/9780132350884 | None | 200 | JSON for Clean Code |
| 4 | POST /members/501/borrow | `{"isbn":"9780132350884"}` | 200 | `{"isbn":"9780132350884","availableCopies":0}` |
| 5 | POST /members/501/return | `{"isbn":"9780132350884"}` | 200 | `{"isbn":"9780132350884","availableCopies":1}` |
| 6 | GET /members/501/fines?daysOverdue=15 | None | 200 | `{"memberId":501,"daysOverdue":15,"tier":"High"}` |
| 7 | GET /books/0000000000000 | None | 404 | `{"error":"Unknown ISBN: 0000000000000"}` |
| 8 | POST /books (missing title) | `{"isbn":"9780132350885","author":"R. Martin","totalCopies":1}` | 400 | `{"error":"title is required"}` |

## curl commands

### Add a book

```bash
curl -i -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","isbn":"9780132350884","author":"R. Martin","totalCopies":1}'
```

### List books

```bash
curl -i http://localhost:8080/books
```

### Get a book by ISBN

```bash
curl -i http://localhost:8080/books/9780132350884
```

### Borrow and return

```bash
curl -i -X POST http://localhost:8080/members/501/borrow \
  -H "Content-Type: application/json" \
  -d '{"isbn":"9780132350884"}'

curl -i -X POST http://localhost:8080/members/501/return \
  -H "Content-Type: application/json" \
  -d '{"isbn":"9780132350884"}'
```

### Fine tier

```bash
curl -i "http://localhost:8080/members/501/fines?daysOverdue=15"
```

### Deliberately invalid requests

```bash
curl -i http://localhost:8080/books/0000000000000

curl -i -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"isbn":"9780132350885","author":"R. Martin","totalCopies":1}'
```

## Actual execution evidence

- [ ] Run all six endpoint requests locally and replace the expected examples with actual response bodies.
- [ ] Run both invalid requests and record the actual error response.
- [ ] Capture terminal or Postman screenshots for the Word submission.
- [ ] Record any unexpected response and link its GitHub issue.
