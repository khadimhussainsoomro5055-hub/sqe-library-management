````markdown
# Development Workflow Notes

## Change Flow

```text
Idea
  │
  ▼
Issue
  │
  │ QA: Clarify requirements and acceptance criteria
  ▼
Branch
  │
  │ QA: Review testability and identify test cases
  ▼
PR (Pull Request)
  │
  │ QA: Review changes and verify tests/coverage
  ▼
Review
  │
  │ QA: Participate in review and raise defects
  ▼
Merge
  │
  │ QA: Confirm approved changes are ready for integration
  ▼
CI (Continuous Integration)
  │
  │ QA: Check automated test results and investigate failures
  ▼
Release
  │
  │ QA: Perform final validation/smoke testing
  ▼
Released
````

## QA Engineer Intervention

| Stage       | QA Engineer's Typical Role                                     |
| ----------- | -------------------------------------------------------------- |
| **Idea**    | Understand the requirement and identify quality risks.         |
| **Issue**   | Review acceptance criteria and clarify expected behavior.      |
| **Branch**  | Identify required test cases and testing scope.                |
| **PR**      | Review the implementation and associated tests.                |
| **Review**  | Participate in review and identify potential defects or risks. |
| **Merge**   | Confirm the change is ready for integration.                   |
| **CI**      | Check automated test results and investigate failures.         |
| **Release** | Perform final smoke/regression checks before release.          |

## Summary

The change moves from an **idea** to an **Issue**, is implemented in a **Branch**, submitted through a **PR**, checked during **Review**, **Merged**, validated by **CI**, and finally delivered as a **Release**. QA can contribute throughout the entire workflow, with heavier testing and validation around the PR, CI, and Release stages.

```
```
