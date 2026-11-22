# Equivalence Partitioning Analysis

## 1. Introduction

Equivalence Partitioning (EP) divides an input domain into groups whose values are expected to produce the same behavior. One representative value is selected from each group to reduce redundant tests. This analysis applies to the Java Library Management System.

## 2. Input 1 — Days Overdue for Fine Tier

| Class | Input Range | Description | Representative | Validity |
|---|---|---|---:|---|
| EC-1 | Less than 0 | Negative overdue days | -3 | Invalid |
| EC-2 | 0 | No overdue fine tier | 0 | Valid |
| EC-3 | 1–7 | Low fine tier | 4 | Valid |
| EC-4 | 8–14 | Medium fine tier | 10 | Valid |
| EC-5 | 15–30 | High fine tier | 20 | Valid |
| EC-6 | 31 or more | Severe fine tier | 45 | Valid |

## 3. Input 2 — Number of Books on Loan

| Class | Input Range | Description | Representative | Validity |
|---|---|---|---:|---|
| EC-7 | 0–5 | Member is within borrowing limit | 3 | Valid |
| EC-8 | 6 or more | Member exceeds borrowing limit | 6 | Invalid state |

The public issue operation prevents a member from reaching six active loans. The executable negative case is therefore a member with five active loans attempting a sixth.

## 4. Input 3 — ISBN

| Class | Input | Description | Representative | Validity |
|---|---|---|---|---|
| EC-9 | Exactly 13 numeric digits | Valid ISBN format | 9780134685991 | Valid |
| EC-10 | Empty or null | ISBN missing | "" | Invalid |
| EC-11 | Fewer than 13 digits | ISBN too short | 123456789 | Invalid |
| EC-12 | More than 13 digits | ISBN too long | 12345678901234 | Invalid |
| EC-13 | Letters or symbols | Non-numeric ISBN | 97801346ABC91 | Invalid |

## 5. Input 4 — Fine Amount Calculation

The monetary calculation uses a separate rate schedule from the fine-tier label:

| Class | Overdue days | Rate | Representative |
|---|---|---|---:|
| EC-14 | 0 | No fine | 0 |
| EC-15 | 1–3 | Rs. 20 per day | 2 |
| EC-16 | 4–7 | Rs. 50 per day | 5 |
| EC-17 | 8 or more | Rs. 100 per day | 9 |
| EC-18 | Negative | Invalid input | -1 |

## 6. Limitation of Equivalence Partitioning

EP does not focus on exact class edges and can miss off-by-one defects. Boundary Value Analysis complements EP by checking values immediately below, at, and above each boundary.

## 7. Automated Test Mapping

- Fine-tier EP: FineTierTest
- Borrowing-limit EP: BorrowLimitTest
- ISBN EP: ISBNValidationTest and ISBNEnforcementTest
- Fine amount EP: CalculateFineBVATest

GitHub Actions run [#44997662882](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/actions/runs/44997662882) completed 84 tests successfully with zero failures.
