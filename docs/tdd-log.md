# Lab 8 – Test-Driven Development Log

## Feature: Rank Books by Popularity

**Branch:** `feature/rank-books-tdd`

**Method:** `rankBooksByPopularity()`

**Description:** Returns library books sorted by total borrowing count in descending order. Books with equal borrowing counts are sorted alphabetically by title.

---

## Cycle 1 – Empty Catalog

**Requirement:** An empty library should return an empty ranking list.

### RED
Created `testRankBooksEmptyCatalogReturnsEmptyList()`.

The test initially failed because `rankBooksByPopularity()` did not exist.

**Commit:** `6dd187e` – test(rank): red - empty catalog returns empty list

### GREEN
Implemented the method to return an empty list.

**Commit:** `ec07daa` – feat(rank): green - return empty list for empty catalog

**Result:** Passed.

---

## Cycle 2 – Single Book

**Requirement:** A library containing one book should return that book in the ranking list.

### RED
Created `testRankBooksSingleBookReturnsThatBook()`.

The test failed because the implementation returned an empty list.

**Commit:** `33a5afc` – test(rank): red - single book appears in ranking

### GREEN
Changed the implementation to return a new list containing the library books.

**Commit:** `dccd1f0` – feat(rank): green - return single book in ranking

**Result:** Passed.

---

## Cycle 3 – Descending Popularity Sorting

**Requirement:** Books with higher borrowing counts must appear before books with lower borrowing counts.

### RED
Created `testRankBooksByPopularityDescending()`.

The test failed because the implementation returned books in insertion order instead of popularity order.

**Commit:** `f8c9ef1` – test(rank): red - books sorted by borrowing count

### GREEN
Added a borrowing counter using a HashMap. Updated `issueBook()` to increase the counter after every successful borrowing. Implemented descending popularity sorting.

**Commit:** `8020f87` – feat(rank): green - sort books by borrowing count

### REFACTOR
Improved test formatting, readability, and assertions.

**Commit:** `33777c5` – refactor(rank): improve popularity test readability

**Result:** Passed.

---

## Cycle 4 – Alphabetical Tie-Breaking

**Requirement:** Books with equal borrowing counts must be sorted alphabetically by title.

Created `testRankBooksWithEqualBorrowingCountsAlphabetically()`.

The test passed immediately because the existing implementation already included alphabetical tie-breaking using `thenComparing()`.

No artificial failure or unnecessary implementation change was introduced.

**Commit:** `4bcc201` – test(rank): verify alphabetical tie-breaking

**Result:** Passed.

---

## Cycle 5 – Never-Borrowed Books

**Requirement:** Books that have never been borrowed should appear after borrowed books. Books with equal zero borrowing counts should be sorted alphabetically.

Created `testNeverBorrowedBooksAppearAfterBorrowedBooks()`.

The test passed immediately because the existing implementation already handled books with no borrowing records using `getOrDefault(bookId, 0)`.

No unnecessary implementation changes were made.

**Commit:** `905c7b3` – test(rank): verify never-borrowed books appear last

**Result:** Passed.

---

## TDD Retrospective

Test-Driven Development helped us build the popularity-ranking feature step by step. Writing tests before implementation helped identify missing functionality, especially when the original method returned books in insertion order. The borrowing counter allowed the system to remember total borrowing activity even after books were returned. The alphabetical tie-breaking and never-borrowed book tests passed immediately because these cases were already supported by the implementation. The complete regression suite passed with 52 tests, confirming that the new feature did not break the existing functionality.

## Final Regression Results

- Total tests: 52
- Successful tests: 52
- Failed tests: 0
- Feature status: Successful
