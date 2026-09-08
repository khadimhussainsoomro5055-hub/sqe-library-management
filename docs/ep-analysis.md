# Equivalence Partitioning Analysis

## 1. Introduction

Equivalence Partitioning (EP) is a black-box testing technique used to divide input values into groups, called equivalence classes. Values in the same class are expected to produce similar system behavior. Instead of testing every possible value, one representative value is selected from each class.

This analysis is for the Java Library Management System.

---

## 2. Input 1 — Number of Days Overdue

The overdue days are divided into equivalence classes according to the fine-tier rules.

| Class | Input Range | Description | Representative Value | Valid/Invalid |
|---|---|---|---:|---|
| EC-1 | Less than 0 | Negative overdue days are invalid | -3 | Invalid |
| EC-2 | 0 | No overdue days, so no fine | 0 | Valid |
| EC-3 | 1–7 | Low fine tier | 4 | Valid |
| EC-4 | 8–14 | Medium fine tier | 10 | Valid |
| EC-5 | 15–30 | High fine tier | 20 | Valid |
| EC-6 | 31 or more | Severe tier | 45 | Valid |

### Selected Representatives

The representative values are:

- EC-1 → -3
- EC-2 → 0
- EC-3 → 4
- EC-4 → 10
- EC-5 → 20
- EC-6 → 45

---

## 3. Input 2 — Number of Books on Loan

The business rule states that a member may have between 0 and 5 books on loan at the same time.

| Class | Input Range | Description | Representative Value | Valid/Invalid |
|---|---|---|---:|---|
| EC-7 | 0–5 books | Member is within the allowed borrowing limit | 3 | Valid |
| EC-8 | 6 or more books | Member has exceeded the borrowing limit | 6 | Invalid |

### Selected Representatives

The representative values are:

- EC-7 → 3 books
- EC-8 → 6 books

---

## 4. Input 3 — ISBN

The ISBN field must contain exactly 13 numeric digits with no letters or symbols.

| Class | Input | Description | Representative Value | Valid/Invalid |
|---|---|---|---|---|
| EC-9 | Exactly 13 numeric digits | Correct ISBN format | 9780134685991 | Valid |
| EC-10 | Empty string | ISBN is missing | "" | Invalid |
| EC-11 | Fewer than 13 digits | ISBN is too short | 123456789 | Invalid |
| EC-12 | Letters or symbols | ISBN contains invalid characters | 97801346ABC91 | Invalid |

### Selected Representatives

The representative values are:

- EC-9 → `9780134685991`
- EC-10 → `""`
- EC-11 → `123456789`
- EC-12 → `97801346ABC91`

---

## 5. Equivalence Partitioning Summary

| Input | Number of Classes | Valid Classes | Invalid Classes |
|---|---:|---|---|
| Days overdue | 6 | EC-2, EC-3, EC-4, EC-5, EC-6 | EC-1 |
| Books on loan | 2 | EC-7 | EC-8 |
| ISBN | 4 | EC-9 | EC-10, EC-11, EC-12 |

A representative value from each equivalence class will be used for automated JUnit testing.

---

## 6. Limitation of Equivalence Partitioning

Equivalence Partitioning does not focus on the exact boundaries between classes. Therefore, it can miss errors that occur at boundary values, such as 7 and 8 overdue days or 30 and 31 overdue days.

Boundary Value Analysis is needed to specifically test these edges. The next lab uses Boundary Value Analysis to address this limitation.

## 7. JUnit Test Execution Results

The EP test cases were implemented using JUnit because the Library Management System is a Java project.

### Fine Tier Tests

- Test class: `FineTierTest`
- Tests found: 6
- Tests successful: 6
- Tests failed: 0

Result: **PASS**

### Borrowing Limit Tests

- Test class: `BorrowLimitTest`
- Tests found: 2
- Tests successful: 2
- Tests failed: 0

Result: **PASS**

### ISBN Validation Tests

- Test class: `ISBNValidationTest`
- Tests found: 6
- Tests successful: 6
- Tests failed: 0

Result: **PASS**

### Overall EP Testing Result

All implemented Equivalence Partitioning tests passed successfully.

| Test Class | Tests | Passed | Failed |
|---|---:|---:|---:|
| FineTierTest | 6 | 6 | 0 |
| BorrowLimitTest | 2 | 2 | 0 |
| ISBNValidationTest | 6 | 6 | 0 |
| **Total** | **14** | **14** | **0** |

The test suite demonstrates that the selected representative values from the equivalence classes produce the expected results.
