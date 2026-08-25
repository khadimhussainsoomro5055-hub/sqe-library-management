# Triage Log

## Sprint Triage — v0.2 Library Management

| Rank | Issue | Defect | Severity | Priority | Decision |
|---|---|---|---|---|---|
| 1 | #6 | System allows unavailable books to be issued | High | P1 | Fix this sprint |
| 2 | #4 | Negative book quantity is accepted | High | P1 | Fix this sprint |
| 3 | #5 | Duplicate book IDs are allowed | High | P1 | Fix this sprint |
| 4 | #7 | Returning a book does not update available quantity | Medium | P2 | Won't fix this sprint |
| 5 | #8 | Book search is case-sensitive | Low | P3 | Won't fix this sprint |

## Triage Decisions

### #6 — Unavailable books can be issued

Ranked first because it directly causes incorrect inventory and allows books with zero available copies to be issued. Its High severity and P1 priority make it the most urgent defect.

### #4 — Negative book quantity is accepted

Ranked second because negative quantities can corrupt inventory records. It has High severity and P1 priority, but #6 has a more direct effect on the book issuing process.

### #5 — Duplicate book IDs are allowed

Ranked third because duplicate IDs can cause incorrect book identification. It is High severity and P1 priority, but it is less immediately harmful than issuing unavailable books.

### #7 — Returning a book does not update available quantity

Ranked fourth because it causes incorrect inventory information but has Medium severity and P2 priority. It will not be fixed this sprint because the impact is lower than the P1 defects.

### #8 — Book search is case-sensitive

Ranked fifth because it has Low severity and P3 priority. Users can still find a book by entering the correct capitalization, so it will not be fixed this sprint.

## Severity and Priority Trade-offs

Severity describes how seriously a defect affects the system, while priority describes how urgently it should be fixed.

Issue #6 and Issue #4 are both High severity and P1 because they can create incorrect inventory data and affect important library operations.

Issue #7 has Medium severity and P2 priority. Although it affects inventory accuracy, it is less urgent than the High/P1 defects.

Issue #8 has Low severity and P3 priority because it is mainly a usability problem and has a simple workaround.

## Sprint Decision

Issues #6, #4, and #5 will be fixed this sprint.

Issues #7 and #8 will not be fixed this sprint because of their lower impact and priority.
