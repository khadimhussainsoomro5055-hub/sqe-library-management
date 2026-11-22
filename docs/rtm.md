# Requirements Traceability Matrix

| Requirement ID | Requirement | Test Case IDs | Coverage Status |
|---|---|---|---|
| REQ-1 | Add a valid new book | TC-001, TC-015 | Covered |
| REQ-2 | Reject duplicate catalog IDs and ISBNs | TC-002, TC-016 | Covered |
| REQ-3 | Reject negative quantities | TC-013 | Covered |
| REQ-4 | Issue an available book | TC-004, TC-008 | Covered |
| REQ-5 | Reject issue when no copies are available | TC-005 | Covered |
| REQ-6 | Return only an active loan and restore inventory | TC-006, TC-007, TC-017 | Covered |
| REQ-7 | Search by title without case sensitivity | TC-014, TC-018 | Covered |
| REQ-8 | Enforce five-loan limit and calculate fines | TC-008, TC-009, TC-010, TC-011, TC-012, TC-019 | Covered |
| REQ-9 | Validate ISBN format and length | TC-003, TC-015, TC-016 | Covered |
| REQ-10 | Prevent duplicate concurrent loans | TC-020 | Covered |

## Coverage Review

Every functional requirement has at least one linked test case. TC-003 is now an executable ISBN-format test rather than a blocked numeric-ID input. The catalog model keeps the internal catalog ID and ISBN as separate fields: this avoids treating an ISBN string as an integer and preserves leading zeroes.

## Traceability Notes

- TC-015 verifies valid ISBN acceptance and retention.
- TC-016 verifies duplicate ISBN rejection across different catalog IDs.
- TC-017 verifies that only the member holding a loan can return it and that a loan cannot be returned twice.
- TC-018 verifies case-insensitive title search and safe handling of blank searches.
- TC-019 verifies the exact monetary fine values at the 3/4 and 7/8 day rate boundaries.
- TC-020 verifies duplicate-loan prevention and confirms inventory is unchanged after a rejected second issue.

Automated execution status is reported by the repository's GitHub Actions workflow. Do not treat a test as executed until the workflow run has completed successfully.
