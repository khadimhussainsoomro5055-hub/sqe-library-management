# Unit Testing Notes

## 1. Overview

This document describes the unit testing improvements performed in the LibraryHub project using Java 21 and JUnit 5.

The purpose is to reduce repeated setup code, improve test readability, isolate dependencies, test edge cases, and prepare the project for automated test execution.

## 2. Shared Test Fixture

A reusable fixture helper was created in `tests/LibraryTestFixture.java`.

The helper provides three methods:

- `resetLibrary()` — clears the library state before a test.
- `addBook()` — creates and adds a book using the supplied details.
- `prepareBorrowingCatalog()` — resets the library and prepares six books for borrowing-limit tests.

These methods reduce duplicated setup code and make the tests easier to maintain.

## 3. Fixture Scopes in JUnit 5

### 3.1 Per-test fixture — `@BeforeEach`

The `@BeforeEach` annotation runs a setup method before every test method.

**Usage in this project:** The borrowing tests initialize a fresh borrowing catalog before each test, while `LibraryFixtureTest` resets the library state before each test.

**Why it is appropriate:** Library data can change when books are added or issued. Resetting the state before every test prevents one test from affecting another.

### 3.2 Class-scoped fixture — `@BeforeAll`

The `@BeforeAll` annotation runs a setup method once before all tests in a test class. In JUnit 5, this method is normally static unless the test class uses a per-class test instance lifecycle.

**Usage in this project:** The earlier version of `BorrowLimitTest` initialized an array containing the book IDs used by its tests. The parameterized version now uses the IDs directly.

**Why it is appropriate:** Constant configuration shared by all tests can be initialized once. More expensive setup may also be shared when it is safe to reuse and does not cause tests to interfere with one another.

### 3.3 Mapping to the Lab's requested scopes

The original lab describes pytest's `module` and `function` fixture scopes. Because this project uses Java and JUnit 5, the closest practical equivalents are:

| Lab concept | JUnit 5 equivalent | Purpose |
|---|---|---|
| Function-scoped fixture | `@BeforeEach` | Prepare independent state for each test |
| Module-scoped fixture | No exact direct equivalent; `@BeforeAll` provides class-level setup | Initialize shared configuration once per test class |

**Note:** JUnit 5's `@BeforeAll` is class-scoped, not automatically module-scoped. It should not be described as an exact equivalent of pytest's module scope.

## 4. Tests Refactored to Use the Fixture

Four JUnit tests were added in `tests/LibraryFixtureTest.java` using the shared fixture helper:

1. `testAddValidBookUsingFixture()` — verifies that a valid book can be added and searched.
2. `testRejectDuplicateBookIdUsingFixture()` — verifies that a duplicate book ID is rejected.
3. `testRejectNegativeQuantityUsingFixture()` — verifies that a negative book quantity is rejected.
4. `testIssueAvailableBookUsingFixture()` — verifies that issuing an available book reduces its quantity.

The borrowing-limit tests also use the shared fixture to prepare their borrowing catalog.

## 5. Total Available Copies

A new method named `totalAvailableCopies()` was implemented in `books.java`.

It calculates the total available copies by adding the quantities of all books in the library.

Three tests were created in `tests/TotalAvailableCopiesTest.java`:

1. Empty catalog — verifies that the total is zero.
2. Single book — verifies the quantity of one book.
3. Multiple books — verifies the sum of quantities for multiple books.

All three tests passed.

## 6. Mocking File I/O

Catalog export functionality was implemented using `exportCatalog()`.

A `CatalogWriter` interface was created to separate the file-writing operation from the catalog logic. A custom `LibraryIOError` exception was also created to handle file-writing failures.

Two tests were added in `tests/ExportCatalogTest.java`:

1. Successful export — uses a fake writer to capture and verify the expected catalog content without writing a real file.
2. Failed export — uses a writer that throws `IOException` and verifies that `LibraryIOError` is raised.

**Note:** The project uses a fake writer (test double) through an injectable interface instead of Mockito because Mockito was not included in the project dependencies.

## 7. Parameterized Borrowing Limit Tests

The borrowing-limit tests were refactored in `tests/BorrowLimitTest.java` using JUnit 5's `@ParameterizedTest` and `@MethodSource`.

One consolidated test method covers six borrowing scenarios:

1. Zero books already borrowed.
2. One book already borrowed.
3. Two books already borrowed.
4. Three books already borrowed.
5. Four books already borrowed.
6. Five books already borrowed.

The first five scenarios allow another book to be borrowed. The final scenario verifies that borrowing is rejected when the member has already reached the limit of five books.

This reduces duplicated test code while keeping each scenario documented through its display name.

## 8. Test Execution and Results

The project uses Java 21 and the JUnit Platform Console Standalone JAR.

### 8.1 Compile the tests

```powershell
javac -cp "lib\junit-platform-console-standalone.jar" -d out src\books_management\books.java src\books_management\CatalogWriter.java src\books_management\LibraryIOError.java tests\LibraryTestFixture.java tests\LibraryFixtureTest.java tests\TotalAvailableCopiesTest.java tests\ExportCatalogTest.java tests\test01.java tests\BorrowLimitTest.java tests\FineTierTest.java tests\FineTierBVATest.java tests\ISBNValidationTest.java
```

### 8.2 Run JUnit tests

```powershell
java -jar lib\junit-platform-console-standalone.jar execute --class-path out --scan-class-path
```

### 8.3 Run the custom test runner

```powershell
java -cp out test01
```

### 8.4 Verbose test output

Command:

```powershell
java -jar lib\junit-platform-console-standalone.jar execute --class-path out --scan-class-path --details=tree
```

Verbose output displays individual test names and their execution results. It is useful when identifying which specific test passed or failed.

The output was saved in `verbose-test-output.txt`.

### 8.5 Short test output

Command:

```powershell
java -jar lib\junit-platform-console-standalone.jar execute --class-path out --scan-class-path --details=summary
```

Short output displays the overall test summary without listing every individual test. It is useful for quickly checking the status of the complete test suite.

The output was saved in `short-test-output.txt`.

### 8.6 Final test results

Both JUnit output formats reported the following results:

| Result | Count |
|---|---:|
| Tests found | 47 |
| Tests started | 47 |
| Tests successful | 47 |
| Tests failed | 0 |

The custom Java test runner also completed successfully:

| Result | Count |
|---|---:|
| Tests passed | 10 |
| Tests failed | 0 |

**Final status:** All 47 JUnit tests and all 10 custom Java tests passed.

## 9. Conclusion

The Lab 7 work introduced reusable test fixtures, total available copies functionality, isolated file-writing tests, and parameterized borrowing-limit tests.

JUnit 5 lifecycle annotations were used to demonstrate per-test and class-level setup. Verbose and short test outputs were generated and compared.

The complete JUnit test suite passed with 47 successful tests and zero failures. The custom Java test runner also passed all 10 tests.

The project is now ready for the final test configuration and GitHub commit.