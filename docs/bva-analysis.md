# Boundary Value Analysis

Boundary Value Analysis (BVA) checks values immediately below, at, and immediately above the boundaries in an input domain. This analysis complements the Equivalence Partitioning work.

## 1. Fine Tier — Days Overdue

Fine-tier categories are: 0 = None, 1–7 = Low, 8–14 = Medium, 15–30 = High, and 31+ = Severe. Negative values are invalid.

| Boundary | Value - 1 | Expected | Boundary | Expected | Value + 1 | Expected |
|---|---:|---|---:|---|---:|---|
| 0 | -1 | Invalid | 0 | None | 1 | Low |
| 1 | 0 | None | 1 | Low | 2 | Low |
| 7 | 6 | Low | 7 | Low | 8 | Medium |
| 8 | 7 | Low | 8 | Medium | 9 | Medium |
| 14 | 13 | Medium | 14 | Medium | 15 | High |
| 15 | 14 | Medium | 15 | High | 16 | High |
| 30 | 29 | High | 30 | High | 31 | Severe |
| 31 | 30 | High | 31 | Severe | 32 | Severe |

JUnit coverage: FineTierBVATest.

## 2. Borrowing Limit

A member may have up to five concurrent loans. The relevant values are four, five and six existing loans before attempting another issue.

| Existing Loans | Attempt | Expected |
|---:|---|---|
| 4 | Borrow one more | Allowed; total becomes 5 |
| 5 | Borrow one more | Rejected; total remains 5 |
| 6 | Borrow one more | Rejected; this state cannot be reached through the public issue operation |

JUnit coverage: BorrowLimitTest exercises the reachable transitions and confirms the sixth issue is rejected.

## 3. ISBN Length

Only 13 numeric digits are accepted.

| Length | Description | Expected |
|---:|---|---|
| 11 | Below boundary | Invalid |
| 12 | Immediately below | Invalid |
| 13 | Exact required length | Valid |
| 14 | Immediately above | Invalid |
| 15 | Above boundary | Invalid |

JUnit coverage: ISBNValidationTest and ISBNEnforcementTest.

## 4. Monetary Fine Calculation Boundaries

The monetary fine rates are independent from the fine-tier labels: 1–3 days at Rs. 20/day, 4–7 days at Rs. 50/day, and 8+ days at Rs. 100/day. Negative days are rejected.

| Boundary | Value - 1 | Expected | Boundary | Expected | Value + 1 | Expected |
|---|---:|---:|---:|---:|---:|---:|
| 0 | -1 | Invalid | 0 | Rs. 0 | 1 | Rs. 20 |
| 3 | 2 | Rs. 40 | 3 | Rs. 60 | 4 | Rs. 200 |
| 4 | 3 | Rs. 60 | 4 | Rs. 200 | 5 | Rs. 250 |
| 7 | 6 | Rs. 300 | 7 | Rs. 350 | 8 | Rs. 800 |
| 8 | 7 | Rs. 350 | 8 | Rs. 800 | 9 | Rs. 900 |

JUnit coverage: CalculateFineBVATest.

## 5. Test Execution

GitHub Actions run [#44997662882](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/actions/runs/44997662882) compiled the application and test sources and completed 84 tests successfully with zero failures. This run validates the current PR branch.
