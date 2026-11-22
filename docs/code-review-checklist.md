# Code Review Checklist

## 1. Correctness
- [ ] Does the code produce the expected output for valid inputs?
- [ ] Are boundaries, invalid inputs and empty collections handled?
- [ ] Are calculations and state changes logically correct?

## 2. Tests
- [ ] Are tests included for every new behavior?
- [ ] Do tests cover normal, negative and boundary cases?
- [ ] Do assertions verify observable behavior rather than merely executing code?

## 3. Readability
- [ ] Are names consistent and meaningful?
- [ ] Is formatting consistent and are methods easy to follow?
- [ ] Are public methods documented where their contract is not obvious?

## 4. Design
- [ ] Does the change fit the existing project structure?
- [ ] Are responsibilities separated and duplication avoided?
- [ ] Are compatibility decisions documented?

## 5. Risk
- [ ] Could the change corrupt catalog or loan state?
- [ ] Are invalid inputs prevented from changing inventory?
- [ ] Are file and runtime failures handled safely?

## 6. Review Retrospective

The most valuable review check was correctness at edge cases. It exposed the percentile denominator problem and missing empty-library handling. The implementation now validates null and empty inputs, rejects members not in the supplied library, handles a one-member library, and tests tied values.

For future reviews, I would add this checkpoint: **Does a rejected operation leave all catalog quantities and loan records unchanged?**

## 7. Review Evidence

The repository contains the code-review PR history and this checklist. The required Lab 9 sequence of at least six inline comments, an accepted GitHub suggestion block, Request Changes followed by a separate reviewer approval, and resolved review threads must be evidenced by the actual PR review conversation. Those UI actions are not represented by a checklist file and should not be claimed without the corresponding GitHub review records.
