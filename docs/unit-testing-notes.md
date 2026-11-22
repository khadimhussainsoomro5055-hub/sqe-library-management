# Unit Testing Notes

## Overview
The project uses Java 21 and JUnit 5. Tests follow Arrange–Act–Assert, reset mutable catalog state before each test, and use parameterized tests for input sweeps.

## Shared Test Fixture
`tests/LibraryTestFixture.java` provides reusable helpers to clear the catalog, add a book, and prepare the borrowing-limit catalog. The helper checks that a fixture book was actually accepted so setup failures do not silently pass.

## Fixture Lifecycles
JUnit 5's `@BeforeEach` runs before every test and is appropriate for mutable library state that must not leak between tests. `@BeforeAll` runs once per test class and is suitable for immutable, expensive setup that can safely be shared. JUnit's class lifecycle is not an exact equivalent of pytest's module-scoped fixture; the project uses JUnit rather than pytest, so this difference is recorded instead of claiming a direct match.

## Test Doubles for File I/O
`CatalogWriter` is an injectable boundary around catalog output. `ExportCatalogTest` supplies a fake writer that captures output without creating a real file, then supplies a writer that throws `IOException` to verify that `LibraryIOError` wraps the failure. This is a lightweight hand-written test double rather than a Mockito mock.

## Parameterized Borrowing Tests
`BorrowLimitTest` uses `@ParameterizedTest` and `@MethodSource` to cover a member with zero through five active loans attempting one additional issue. The four-loan case reaches the maximum of five; the five-loan case verifies rejection of a sixth issue. A six-loan precondition cannot be reached through the public issue operation because the limit prevents it.

## Test Coverage Added
- `FineTierTest` and `FineTierBVATest` cover fine-tier equivalence classes and cut-offs.
- `ISBNValidationTest` covers null, empty, malformed and length-boundary ISBN values.
- `CalculateFineBVATest` covers the separate monetary-rate cut-offs at 3/4 and 7/8 days.
- `DuplicateBorrowingTest` covers repeated loans by the same member.
- `ISBNEnforcementTest` verifies ISBN validation at book construction and duplicate ISBN rejection.
- `ReturnBookTest` verifies inventory restoration, wrong-member rejection and double-return prevention.
- `SearchBookTest` verifies case-insensitive search and null/blank handling.
- `MemberTest` covers percentile boundaries, ties and invalid inputs.
- `TotalAvailableCopiesTest` and `ExportCatalogTest` cover inventory totals and isolated file output.

## Running the Tests
The GitHub Actions workflow downloads the JUnit Platform Console Standalone JAR, compiles every source and test file, and runs the discovered JUnit tests:

```powershell
mkdir lib
curl.exe -L -o lib/junit-platform-console-standalone.jar https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar
mkdir out
javac -cp "lib\junit-platform-console-standalone.jar" -d out src\main.java src\books_management\*.java tests\*.java
java -jar lib\junit-platform-console-standalone.jar execute --class-path out --scan-class-path --details=summary
```

Use `--details=tree` to inspect individual test names. Use `--details=summary` for a compact suite result.

## Latest CI Result

GitHub Actions run [#44997662882](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/actions/runs/44997662882) compiled the application and all test sources successfully and completed the JUnit suite:

| Result | Count |
|---|---:|
| Containers successful | 23 |
| Tests found | 84 |
| Tests started | 84 |
| Tests successful | 84 |
| Tests failed | 0 |

The SonarCloud job was skipped because the repository's `SONAR_ENABLED` variable is not enabled. This JUnit result does not imply a SonarCloud Quality Gate pass.
