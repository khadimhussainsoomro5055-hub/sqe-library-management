# Functional Requirements

## REQ-1 — Add a book
The system shall allow a new book to be added to the library when its catalog ID and ISBN are unique and its details are valid.

## REQ-2 — Unique identifiers
The system shall reject a book with a duplicate catalog ID or duplicate ISBN.

## REQ-3 — Quantity validation
The system shall reject a book with a negative available quantity.

## REQ-4 — Issue an available book
The system shall allow a member to issue a book when at least one copy is available and the member is eligible to borrow it.

## REQ-5 — Availability
The system shall prevent a book from being issued when no copies are available.

## REQ-6 — Return a loan
The system shall allow a member to return only a book that is currently recorded as borrowed by that member, restoring one available copy.

## REQ-7 — Search
The system shall allow users to search for a book by its name without case sensitivity.

## REQ-8 — Borrowing limit and fines
The system shall enforce a maximum of five concurrent loans per member and calculate overdue fines according to the configured rate tiers.

## REQ-9 — ISBN format
The system shall accept ISBN values containing exactly 13 numeric digits and reject null, empty, malformed, or incorrectly sized values.

## REQ-10 — Duplicate loan prevention
The system shall prevent a member from borrowing the same catalog book more than once at the same time.
