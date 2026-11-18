# SonarCloud Findings – SQE Library Management

## 1. Analysis Information

- **Project:** SQE Library Management
- **Repository:** khadimhussainsoomro5055-hub/sqe-library-management
- **Branch:** main
- **Analysis Tool:** SonarCloud
- **Language:** Java 21

## 2. Findings

### Finding 1: TestCases should contain tests

| Field | Details |
|---|---|
| Rule ID | java:S2187 |
| File | `tests/MemberTest.java` |
| Original Line | 5 |
| Severity | Blocker |
| Software Quality | Maintainability |
| Status | Fixed |

**Description:**

SonarCloud reported that the `MemberTest` class did not contain any test methods. An empty test class does not provide meaningful test coverage.

**Resolution:**

Added JUnit 5 test methods to `MemberTest.java` to test the member borrowing percentile functionality.

The tests cover:
- Middle borrowing percentile
- Single-member library
- Lowest borrowing percentile
- Highest borrowing percentile
- Empty library validation
- Null library validation

**Verification:**

The updated project test suite passed all 58 tests.

## 3. Analysis Warnings

The initial analysis reported missing Java library configuration:

- `sonar.java.libraries`
- `sonar.java.test.libraries`

These were addressed by configuring the JUnit library paths and updating the GitHub Actions workflow to download the JUnit standalone JAR.

The subsequent analysis no longer reported these warnings.

## 4. Summary

The initial analysis identified one confirmed SonarCloud issue, which was fixed by adding JUnit tests to `MemberTest.java`.

No additional open issues were displayed in the latest analysis. Therefore, eight distinct findings were not available to document from the observed scan results.

Further code-quality improvements will be evaluated separately.