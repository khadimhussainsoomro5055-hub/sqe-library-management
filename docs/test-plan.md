# Library Management System Test Plan

## 1. Introduction

The purpose of this test plan is to verify the functional behavior of the Library Management System. Testing will focus on book management, book issuing, book returning, searching, and book information. The plan will help determine whether the system meets its defined functional requirements.

## 2. Test Items

The following parts of the system will be tested:

- Book creation and addition
- Book ID validation
- Book quantity validation
- Book issuing
- Book returning
- Book searching
- Book information display
- Library inventory management

## 3. Features to be Tested

The following features will be tested:

- Adding a valid book
- Rejecting duplicate book IDs
- Rejecting negative book quantities
- Issuing books when copies are available
- Preventing issues when no copies are available
- Returning issued books
- Rejecting invalid book returns
- Searching books by name
- Displaying correct book information

## 4. Features Not to be Tested

The graphical user interface is not included because the current project is a Java-based library module and does not contain a GUI. Database integration and external services are also outside the scope of this test plan. Testing will focus only on the functionality available in the current Java codebase.

## 5. Test Approach

Testing will use functional and negative testing techniques. Each test case will have a unique ID, requirement reference, preconditions, steps, expected result, priority, and type. Negative tests will check how the system handles invalid inputs and unavailable operations. Regression testing will be performed after defects are fixed.

## 6. Pass/Fail Criteria

The test execution will be considered successful when at least **95% of the planned test cases pass**. **Zero Critical or High severity defects** should remain open for release. A test case will be marked Pass only when the actual result matches the expected result. Any failed test case will be documented and linked to a GitHub issue.

## 7. Test Deliverables

The following testing documents will be produced:

- `docs/test-plan.md`
- `docs/test-cases.md`
- `docs/rtm.md`
- Manual test execution results
- GitHub issues for failed tests

## 8. Environmental Needs

Testing will be performed on a Windows computer using a Java development environment. The project source code will be stored in GitHub and tested from the repository. Tests will be executed against the current Library Management System source code.

## 9. Schedule

| Activity | Duration |
|---|---|
| Test Plan preparation | 60 minutes |
| Test Case preparation | 75 minutes |
| Requirements Traceability Matrix | 30 minutes |
| Manual Test Execution | 35 minutes |

The activities will be completed in the order specified in the lab. Defects found during testing will be documented and linked to the related test cases.

## 10. Risks

Incomplete requirements may result in insufficient test coverage. Changes to the source code during testing may affect previously tested functionality and require regression testing. Some test cases may also be blocked when the current implementation does not provide the required functionality.
