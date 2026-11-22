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

## 6. Lessons Learned

The most valuable checklist item was checking **boundaries, invalid inputs and empty collections**. It helped identify the percentile calculation issue and the missing handling for an empty library. These checks reminded me to test edge cases, not just normal input.

For the next review, I would add this item: **Does a rejected operation leave catalog quantities and loan records unchanged?** This will help catch unintended side effects when an operation fails.

## 7. Review Evidence

The repository contains the code-review PR history and this checklist. The required Lab 9 sequence of at least six inline comments, an accepted GitHub suggestion block, Request Changes followed by a separate reviewer approval, and resolved review threads must be evidenced by the actual PR review conversation. Those UI actions are not represented by a checklist file and should not be claimed without the corresponding GitHub review records.
