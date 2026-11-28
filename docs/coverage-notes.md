# Lab 11 — Automated Test Coverage

## Project adaptation

The handout demonstrates `pytest-cov` for Python. This repository is implemented in Java 21 and uses JUnit 5, so this lab applies the same coverage outcomes with JaCoCo. CI instruments production classes separately from test classes, generates HTML/XML/CSV reports, and enforces a minimum aggregate line-coverage threshold.

## Baseline report — original main-branch suite

The baseline was measured on the original `main` source and test suite using JaCoCo before the new Lab 11 tests were added. GitHub Actions run #44 completed successfully with 84 tests passing and no failures.

| Metric | Baseline |
|---|---:|
| Line coverage | **68.24%** (159/233) |
| Branch coverage | **71.64%** (96/134) |
| JUnit tests | 84 passed, 0 failed |
| Report artifact | `lab11-original-baseline` |

## Three weakest source files in the baseline

The following were selected from the baseline JaCoCo HTML/XML report.

### 1. `main.java` — 0.00% line coverage

The demo entry point was not executed by the original test suite. The report showed the executable body (approximately lines 5–124) as uncovered, including catalog setup, add/duplicate operations, issue/return, search, fine calculation and final output. The original suite had no test invoking `main.main()`.

### 2. `books.java` — 81.53% line coverage

Baseline uncovered lines included:
- Constructor and validation paths: 41, 49, 52 and 89.
- Compatibility and catalog validation paths: 80, 96, 111–112 and 204.
- Borrow/return negative and defensive paths: 116, 130, 139, 144, 158 and 160.
- Search and member-count edge cases: 180–181 and 186.
- Output and export paths: 247–249, 273 and 293–295.

These correspond to invalid identifiers/names/authors/quantities, the deprecated compatibility getter/method, missing/invalid inputs, negative return/search outcomes, null member count, display output, invalid export arguments and the default file-writing overload.

### 3. `Member.java` — 85.29% line coverage

The baseline report marked constructor validation and untested access/update behavior as missed, including lines 12, 15, 26 and 33–34. These correspond to negative member IDs, invalid names, the name getter and the borrowed-count setter path.

`CatalogWriter` has no executable lines of its own; `LibraryIOError` had 100% line coverage in the baseline and therefore was not selected as a weak file.

## Coverage-gap tests added

- `LibraryCoreEdgeCasesTest` covers invalid book constructor arguments, text normalization, legacy constructor/getter behavior, null and duplicate catalog additions, negative quantity updates, invalid borrowing/return inputs, no-copy handling, fine calculation bands, console output, file export, missing search results, and defensive malformed internal loan state.
- `MemberConstructorEdgeCasesTest` covers invalid member IDs/names/counts, name trimming, getter/setter behavior and percentile lookup by ID.
- `MainExecutionTest` executes the application entry point, checks its important output, instantiates the entry-point class and covers both search-result display outcomes.
- The existing borrow/return lifecycle test now checks available-copy and borrowed-count values after issue, return and re-issue instead of only checking successful method returns.

The JaCoCo report is the source of truth for final missed lines and branches. The baseline gaps above were used to prioritize these tests; the final report determines whether any defensive branches remain unreachable through the public API.

## Coverage versus test quality

A coverage percentage only indicates that code executed; it does not prove that the test checked the correct result. The earlier `memberCanBorrowAgainAfterReturningBook` test asserted successful issue/return operations and the final borrowed count, but did not verify the inventory count at each state transition. That was a coverage trap: the method could execute while an incorrect quantity update remained undetected.

The test now asserts:
1. Available quantity decreases from 3 to 2 after issue.
2. Available quantity returns from 2 to 3 after return.
3. Borrowed count returns to zero after return.
4. Re-issue decreases the quantity again and restores the member's borrowed count to one.

## Threshold enforcement

The workflow runs `scripts/check_coverage.py coverage/jacoco.xml 85` after report generation. It fails CI if aggregate production line coverage is below 85%. Branch coverage is printed for review, and the HTML report exposes the remaining missed paths.

The threshold is enforced in the Java CI workflow rather than adding Python tooling to this Java-only repository.

## Measured results

| Metric | Baseline | Final verified run |
|---|---:|---:|
| Line coverage | 68.24% (159/233) | 98.31% (233/237) |
| Branch coverage | 71.64% (96/134) | 96.32% (131/136) |
| JUnit tests passed | 84 | 101 |
| JUnit tests failed | 0 | 0 |
| 85% line threshold | Not enforced in baseline | Passed |

The final figures above are from the verified JaCoCo run after the production/test class separation. A later commit adds a small number of further branch tests and the search-result helper refactor; the newest run must replace these figures if its report differs.

## Evidence

- Baseline report artifact: GitHub Actions run #44, artifact `lab11-original-baseline`.
- Final report artifact: GitHub Actions run #43, artifact `lab11-coverage-baseline`.
- Each artifact contains `coverage/html/index.html`, `coverage/jacoco.xml`, `coverage/jacoco.csv` and execution data.
- [Final workflow run](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/actions/runs/46139525343)
- [Baseline workflow run](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/actions/runs/46139534446)

## Screenshot checklist

- [ ] Capture the baseline JaCoCo HTML summary table from the downloaded baseline artifact.
- [ ] Capture the final JaCoCo HTML summary table from the downloaded final artifact after the latest test commit.
- [ ] Attach both images to the lab submission.

Screenshots must come from the actual JaCoCo HTML reports. No synthetic terminal output or estimated coverage screenshot should be submitted.
