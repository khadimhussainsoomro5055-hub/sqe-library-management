# Contributing Guidelines

Thank you for your interest in contributing to the LibraryHub project.

These guidelines explain how contributors can report issues, make changes, and submit pull requests.

## 1. Reporting Issues

Before reporting a new issue:

- Check whether the issue already exists.
- Use the appropriate GitHub issue template.
- Clearly describe the problem.
- Provide steps to reproduce the issue.
- Explain the expected and actual results.

Use the Bug Report template for reporting defects and the Feature Request template for suggesting new functionality.

## 2. Branching Strategy

Do not make changes directly to the main branch.

Create a separate branch for every new feature, bug fix, or test improvement.

Branch naming examples:

| Branch Type | Example |
|---|---|
| Feature | feature/book-search |
| Bug Fix | fix/duplicate-book-id |
| Testing | test/borrowing-limit |
| Documentation | docs/update-readme |

Create a new branch using:

```bash
git switch main
git pull origin main
git switch -c feature/your-feature-name
```

## 3. Coding Guidelines

Contributors should:

- Write clean and readable code.
- Use meaningful variable and method names.
- Follow the existing project structure.
- Avoid unnecessary code duplication.
- Keep methods focused on one responsibility.
- Add comments where clarification is needed.
- Update documentation when necessary.

## 4. Commit Guidelines

Use small and meaningful commits.

Follow the Conventional Commit message format:

```text
type: short description
```

Common commit types:

| Type | Purpose |
|---|---|
| feat | Add a new feature |
| fix | Fix a bug |
| test | Add or update tests |
| docs | Update documentation |
| refactor | Improve code structure |
| chore | Maintenance tasks |

Examples:

```bash
git add .
git commit -m "feat: add book search functionality"
```

```bash
git commit -m "fix: prevent duplicate book IDs"
```

```bash
git commit -m "test: add borrowing limit test cases"
```

Avoid unclear commit messages such as:

- update
- changes
- final commit
- modifications

## 5. Testing Guidelines

Before submitting a pull request:

- Run the existing automated tests.
- Add test cases for new functionality.
- Update existing tests when behaviour changes.
- Check boundary and invalid input conditions.
- Make sure existing functionality is not broken.

All relevant tests should pass before merging.

## 6. Pull Request Guidelines

After completing your changes, push your branch:

```bash
git push -u origin feature/your-feature-name
```

Create a pull request on GitHub targeting the `main` branch.

Every pull request should:

- Have a clear title.
- Explain the purpose of the changes.
- Describe the implemented functionality or fix.
- Include test results.
- Link the related issue.
- Complete the pull request checklist.

If the pull request resolves an issue, use a closing reference such as:

```text
Fixes #12
```

## 7. Code Review

Every contributor should participate in code reviews.

Reviewers should check:

- Code correctness.
- Test coverage.
- Readability.
- Code organization.
- Possible defects.
- Security and reliability risks.

Review comments should be respectful and constructive.

Contributors should address requested changes before requesting another review.

## 8. Merging Changes

Before merging a pull request:

- Ensure required reviews are completed.
- Ensure relevant tests pass.
- Resolve review comments.
- Check for merge conflicts.
- Confirm that the pull request is ready.

Changes should be merged into `main` only after the required checks and reviews are satisfied.

## 9. General Guidelines

- Keep contributions related to the project.
- Do not commit unnecessary generated files.
- Do not commit downloaded JAR files or compiled `.class` files.
- Keep documentation updated.
- Communicate clearly with other contributors.

Thank you for contributing to LibraryHub!
