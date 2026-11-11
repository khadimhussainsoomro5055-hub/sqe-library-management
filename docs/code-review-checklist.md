
# Code Review Checklist

## 1. Correctness
- [ ] Does the code produce the expected output?
- [ ] Are boundary conditions and edge cases handled correctly?
- [ ] Are there any logical errors or incorrect calculations?

## 2. Tests
- [ ] Are appropriate test cases included?
- [ ] Do tests cover normal cases and edge cases?
- [ ] Do all relevant tests pass?

## 3. Readability
- [ ] Are variable and method names meaningful?
- [ ] Is the code properly formatted and easy to understand?
- [ ] Are comments and documentation provided where necessary?

## 4. Design
- [ ] Does the implementation follow the existing project structure?
- [ ] Is unnecessary code duplication avoided?
- [ ] Are responsibilities divided appropriately between methods and classes?

## 5. Risk
- [ ] Could the change introduce security vulnerabilities?
- [ ] Could it negatively affect existing functionality?
- [ ] Are there any performance concerns?

## 6. Code Review Retrospective

### What I learned
I learned that code review helps identify mistakes that may not be noticed during initial development. It also improves code quality, readability, and reliability.

### Problems identified
- Integer division caused incorrect percentile calculations.
- Empty library input was not handled.
- Tests printed results without checking expected values.
- Method documentation was missing.

### How the review improved the code
I fixed the calculation, added empty-library validation, wrote JavaDoc documentation, and added assertions for normal, single-member, and empty-library cases.

### Lessons for future reviews
- Check edge cases carefully.
- Use assertions to verify expected results.
- Write clear method documentation.
- Review code for correctness, readability, design, and risk.
