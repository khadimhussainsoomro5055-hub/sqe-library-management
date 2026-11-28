# LibraryHub API — SQE Lab 12

A Java 17+ Spring Boot REST API with JUnit 5 and REST Assured integration tests.

## Run locally

```bash
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

## Run tests

```bash
mvn test
```

## Endpoints

| Method | Path | Purpose |
|---|---|---|
| POST | /books | Add a book |
| GET | /books | List all books |
| GET | /books/{isbn} | Find a book |
| POST | /members/{id}/borrow | Borrow a book |
| POST | /members/{id}/return | Return a book |
| GET | /members/{id}/fines?daysOverdue=n | Get fine tier |

See [docs/api-exploration.md](docs/api-exploration.md) for manual request examples and the exploration record.

## Test coverage

- Six positive endpoint scenarios.
- Negative and boundary cases N1–N7 and N9.
- Fine tier parameterized boundary tests.
- JSON Schema contract validation.
- A generous 1000 ms response-time check.

All integration tests start the real Spring Boot application on a random port and reset the service state before each test.
