# Boundary Value Analysis

## 1. Introduction

Boundary Value Analysis (BVA) is a black-box testing technique that focuses on values at the boundaries of input ranges. It tests the boundary value itself and the values immediately below and above it.

This analysis is for the Java Library Management System and complements the Equivalence Partitioning performed in Lab 5.

## 2. Input 1 — Fine Tier / Days Overdue

The fine-tier rules are:

- 0 days → None
- 1–7 days → Low
- 8–14 days → Medium
- 15–30 days → High
- 31 or more days → Severe
- Negative days → Invalid

The important boundaries are 0, 1, 7, 8, 14, 15, 30, and 31.

| Boundary | Value - 1 | Expected | Boundary Value | Expected | Value + 1 | Expected |
|---|---:|---|---:|---|---:|---|
| 0 | -1 | Invalid | 0 | None | 1 | Low |
| 1 | 0 | None | 1 | Low | 2 | Low |
| 7 | 6 | Low | 7 | Low | 8 | Medium |
| 8 | 7 | Low | 8 | Medium | 9 | Medium |
| 14 | 13 | Medium | 14 | Medium | 15 | High |
| 15 | 14 | Medium | 15 | High | 16 | High |
| 30 | 29 | High | 30 | High | 31 | Severe |
| 31 | 30 | High | 31 | Severe | 32 | Severe |

## 3. Input 2 — Number of Books on Loan

The Lab 5 borrowing rule allows a member to have between 0 and 5 books on loan at the same time.

For BVA, we focus on the maximum boundary of 5 books. The member attempts to borrow one additional book.

| Current Books | Action | Expected Result |
|---:|---|---|
| 4 | Attempt to borrow one more | Allowed |
| 5 | Attempt to borrow one more | Rejected |
| 6 | Attempt to borrow one more | Rejected |

### Borrowing-Limit Boundary Values

The selected boundary values are:

```text
4, 5, 6
```

## 4. Input 3 — ISBN Length

The ISBN rule requires exactly 13 numeric digits.

For BVA, we focus on the boundary of 13 digits.

| ISBN Length | Description | Expected Result |
|---:|---|---|
| 11 | Below the valid length | Invalid |
| 12 | Immediately below the valid length | Invalid |
| 13 | Required valid length | Valid |
| 14 | Immediately above the valid length | Invalid |
| 15 | Above the valid length | Invalid |

### ISBN Boundary Values

The selected boundary values are:

```text
11, 12, 13, 14, 15
```

