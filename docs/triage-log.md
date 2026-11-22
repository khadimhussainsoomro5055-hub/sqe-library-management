# Defect Triage Log

## LibraryHub Defect Review

| Issue | Defect | Severity | Priority | Current handling |
|---|---|---|---|---|
| #7 | Return inventory not restored | Medium | P2 | Fix and regression test in PR #25 |
| #8 | Case-sensitive book search | Low | P3 | Fix and regression test in PR #25 |
| #14 | Monetary fine boundaries not covered | Low | P2 | Boundary suite added in PR #25 |
| #15 | ISBN validation not enforced on book creation | High | P1 | Explicit ISBN constructor and tests in PR #25 |
| #16 | Catalog ID and ISBN naming ambiguity | Low | P3 | Separated catalog ID and ISBN fields in PR #25 |
| #17 | Duplicate concurrent borrowing | High | P1 | Rejection logic and regression tests in PR #25 |

## Triage Rationale

Severity describes the technical impact of a defect, while priority describes how quickly it should be addressed. Inventory corruption, invalid ISBN acceptance and duplicate active loans affect core library operations and are treated as higher priority than search usability or missing boundary coverage.

## Resolution References

The fixes and regression tests are grouped in [PR #25](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/pull/25). Issues should be closed by the pull request after the change has been reviewed and merged. Until then, they remain linked to the proposed fix rather than being marked complete prematurely.
