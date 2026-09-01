# Library Management System Test Plan

## 1. Introduction

The purpose of this test plan is to verify the functional behavior of the Library Management System. Testing will focus on book management, book issuing, book returning, searching, and inventory information. The plan provides a structured approach for determining whether the system meets its defined functional requirements.

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
- Handling invalid book returns
- Searching books by name
- Displaying correct book information

## 4. Features Not to be Tested

The graphical user interface is not included in this test plan because the current project is a Java-based library module rather than a graphical application. Testing will focus on the available Java classes and their functional behavior. External systems and database integration are also outside the scope of this test plan.

## 5. Test Approach

Testing will primarily use functional and negative testing techniques. Each test case will have a unique ID, requirement reference, preconditions, steps, expected result, priority, and type. Negative tests will verify that the system correctly handles invalid inputs and unavailable operations. Regression testing will be performed after defects are fixed to ensure existing functionality continues to work.

## 6. Pass/Fail Criteria

The test execution will be considered successful when at least 95% of the planned test cases pass. No Critical or High severity defects should remain open for release. Any failed test case must be investigated and documented with its corresponding defect issue. A test case will be marked Pass only when its actual result matches the expected result.

## 7. Test Deliverables

The following testing documents will be produced:

- `docs/test-plan.md`
- `docs/test-cases.md`
- `docs/rtm.md`
- Manual test execution results
- GitHub defect issues for failed tests

## 8. Environmental Needs

Testing will be performed on a Windows computer using the Java development environment. The project source code will be stored in GitHub and tested from the repository. Tests will be executed against the current Library Management System source code.

## 9. Schedule

| Activity | Duration |
|---|---|
| Test Plan preparation | 60 minutes |
| Test Case preparation | 75 minutes |
| Requirements Traceability Matrix | 30 minutes |
| Manual Test Execution | 35 minutes |

The testing activities will be completed in the order specified in the lab. Defects discovered during manual execution will be documented and linked to the relevant test cases.

## 10. Risks

Incorrect or incomplete requirements may result in insufficient test coverage. Changes to the source code during testing may affect previously tested functionality and require regression testing. Some functionality may also be difficult to test if the current implementation does not provide a direct interface for the required operation.
