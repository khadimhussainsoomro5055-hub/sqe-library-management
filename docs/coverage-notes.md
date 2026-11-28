# Lab 11 — Automated Test Coverage

## Project adaptation

The handout demonstrates `pytest-cov` for Python. This repository is implemented in Java 21 and uses JUnit 5, so this lab applies the same coverage outcomes with JaCoCo. The CI workflow instruments the compiled application classes, generates HTML/XML/CSV reports, and checks the aggregate line-coverage threshold.

## Baseline results

The baseline must be taken from a real JaCoCo execution of the test suite before interpreting coverage. The initial report has not yet been retrieved from GitHub Actions, so no percentage is estimated or invented here.

| Metric | Baseline |
|---|---:|
| Line coverage | Pending first JaCoCo run |
| Branch coverage | Pending first JaCoCo run |
| HTML report | `coverage/html/index.html` artifact |
| Machine-readable report | `coverage/jacoco.xml` |

The GitHub Actions artifact is named `lab11-coverage-baseline`. Download it from the workflow run and inspect `coverage/html/index.html` and `coverage/jacoco.xml`.

## Weakest source files and gap analysis

The three weakest production source files must be selected from the generated per-file HTML report, not guessed from source size or intuition. Record the exact missed lines and branch paths from that report here after the baseline run.

| Source file | Baseline line coverage | Missed lines / branches | Follow-up tests |
|---|---:|---|---|
| Pending measured report | — | — | — |
| Pending measured report | — | — | — |
| Pending measured report | — | — | — |

## Coverage-gap tests added in this branch

- `LibraryCoreEdgeCasesTest` exercises constructor validation, trimming, legacy construction, duplicate catalog IDs, invalid quantities, invalid borrowing/return inputs, no-copy handling, fine calculation bands, console output and actual file export.
- `MemberConstructorEdgeCasesTest` exercises member constructor validation, name normalization, setter behavior and percentile lookup by member ID.
- `MainExecutionTest` exercises the command-line entry point and verifies important output.
- The existing borrowing lifecycle test was strengthened to assert available-copy counts and member loan counts after issue, return and re-issue. It now checks observable state rather than only checking that calls returned successfully.

These tests target plausible unexercised branches found by source review. The final report must confirm which of these were actually uncovered in the baseline and which gaps remain.

## Coverage versus test quality

A coverage percentage only indicates that code executed; it does not prove that the test checked the correct result. The earlier `memberCanBorrowAgainAfterReturningBook` test asserted successful issue/return operations and the final borrowed count, but did not verify the inventory count at each state transition. That was a coverage trap: the method could execute while an incorrect quantity update remained undetected.

The test now asserts:
1. Available quantity decreases from 3 to 2 after issue.
2. Available quantity returns from 2 to 3 after return.
3. Borrowed count returns to zero after return.
4. Re-issue decreases the quantity again and restores the member's borrowed count to one.

## Threshold enforcement

The workflow runs `scripts/check_coverage.py coverage/jacoco.xml 85` after report generation. It fails the CI job if aggregate line coverage is below 85%. Branch coverage is also printed for review; the report should be used to judge whether conditional paths have been exercised meaningfully.

The threshold is intentionally enforced in the Java CI workflow rather than adding Python tooling to this Java-only repository.

## Baseline vs. final results

| Metric | Baseline | Final |
|---|---:|---:|
| Line coverage | Pending CI report | Pending CI report |
| Branch coverage | Pending CI report | Pending CI report |
| JUnit tests passed | 84 (previous recorded run) | Pending new CI run |
| JUnit tests failed | 0 (previous recorded run) | Pending new CI run |

The previous 84-test result is documented in `docs/unit-testing-notes.md`; it is not a result for the new tests in this branch.

## Evidence checklist

- [ ] Download baseline `lab11-coverage-baseline` artifact and capture the HTML summary.
- [ ] Record baseline aggregate line and branch coverage.
- [ ] Identify the three weakest production files and exact missed lines/branches.
- [ ] Review the final HTML report and confirm the targeted gaps are closed.
- [ ] Capture the final HTML summary.
- [ ] Confirm CI passes the 85% line-coverage threshold.
- [ ] Record final line/branch percentages and final JUnit result above.

Screenshots must come from the actual GitHub Actions artifact/report. No synthetic terminal output or estimated coverage screenshot should be submitted.
