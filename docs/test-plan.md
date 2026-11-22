# Library Management System Test Plan

## 1. Introduction

The purpose of this test plan is to verify the functional behavior of LibraryHub. Testing covers book catalog management, ISBN validation, book issuing and returning, search, member borrowing limits, overdue fine calculation, popularity ranking, percentile calculation and catalog export.

## 2. Test Items

- Book creation, unique catalog IDs and ISBN validation
- Book quantity validation
- Book issuing and duplicate-loan prevention
- Book returns and inventory restoration
- Case-insensitive book search
- Borrowing-limit enforcement
- Fine-tier classification and monetary fine calculation
- Popularity ranking and member percentile calculation
- Catalog export and I/O error handling

## 3. Features to be Tested

Valid and invalid book creation, duplicate identifiers, malformed ISBNs, negative quantities, available and unavailable issues, repeated loans, valid and invalid returns, search input handling, borrowing-limit boundaries, fine boundaries, ranking order, percentile edge cases and catalog export success/failure.

## 4. Features Not to be Tested

A graphical user interface is excluded because the current project is a Java library module and does not contain a GUI. Database integration and external services are also excluded because the current implementation uses in-memory collections and local file export. These exclusions keep the test scope aligned with the code that exists.

## 5. Test Approach

JUnit 5 is used for automated unit and regression tests. Equivalence Partitioning and Boundary Value Analysis guide input selection. Parameterized tests cover related cases, shared fixtures reset mutable state, and an injectable writer isolates catalog file output. Pull requests trigger compilation and the full JUnit suite through GitHub Actions.

## 6. Pass/Fail Criteria

The planned suite should achieve at least 95% passing tests, with zero open Critical or High severity defects before release. A test passes only when the actual result matches the expected result. Failed tests must be recorded with a reproducible GitHub issue and linked to the corrective pull request.

## 7. Test Deliverables

- `docs/test-plan.md`
- `docs/test-cases.md`
- `docs/requirements.md`
- `docs/rtm.md`
- EP and BVA analysis
- JUnit test classes and CI execution results
- GitHub issues and pull requests for defects

## 8. Environmental Needs

The project requires JDK 21, Git, GitHub and JUnit 5. The GitHub Actions environment uses Ubuntu and downloads the JUnit Platform Console Standalone JAR from Maven Central. Tests run against the in-memory Java implementation.

## 9. Schedule

| Activity | Duration |
|---|---|
| Test Plan preparation | 60 minutes |
| Test Case preparation | 75 minutes |
| Requirements Traceability Matrix | 30 minutes |
| Manual Test Execution | 35 minutes |
| Regression and boundary testing | As required by the lab sequence |

## 10. Risks

Requirements that are not reflected in the code may produce blocked tests. Shared mutable catalog state can cause order-dependent tests unless it is reset before each test. Fine-tier labels and monetary fine rates use different boundaries, so both need separate coverage. SonarCloud analysis depends on repository configuration and a valid token; a skipped analysis must not be reported as a passed Quality Gate.
