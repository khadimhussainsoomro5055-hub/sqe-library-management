# LibraryHub API — Manual API Requests

This document contains sample requests for checking the LibraryHub API locally. Start the application before sending the requests:

```bash
mvn spring-boot:run
```

The server listens on `http://localhost:8080`.

## Requests

### Add a book

```bash
curl -i -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"title":"Clean Code","isbn":"9780132350884","author":"R. Martin","totalCopies":1}'
```

### List all books

```bash
curl -i http://localhost:8080/books
```

### Find a book by ISBN

```bash
curl -i http://localhost:8080/books/9780132350884
```

### Borrow a book

```bash
curl -i -X POST http://localhost:8080/members/501/borrow \
  -H "Content-Type: application/json" \
  -d '{"isbn":"9780132350884"}'
```

### Return a book

```bash
curl -i -X POST http://localhost:8080/members/501/return \
  -H "Content-Type: application/json" \
  -d '{"isbn":"9780132350884"}'
```

### Check a fine tier

```bash
curl -i "http://localhost:8080/members/501/fines?daysOverdue=15"
```

### Invalid requests

Find a book using an ISBN that does not exist:

```bash
curl -i http://localhost:8080/books/0000000000000
```

Try to add a book without its title:

```bash
curl -i -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"isbn":"9780132350885","author":"R. Martin","totalCopies":1}'
```

## Recording results

For each request, record the HTTP status and response body returned by your local run. The output may be copied into the lab report, and screenshots can be taken from the terminal or Postman. Keep the actual output from your run; do not use sample output as captured evidence.

## Automated tests

Run the automated tests from this directory:

```bash
mvn test
```

The tests cover successful requests, invalid requests, fine-tier boundaries, the book response schema and response time.