# Development Workflow Notes

## Change Flow

```text
Idea → Issue → Branch → Pull Request → Review → Merge → CI → Release
```

QA clarifies acceptance criteria at the issue stage, identifies tests before implementation, checks the code and tests during review, verifies the CI result after merge, and performs smoke/regression checks before a release.

## Branch and Commit Conventions

Feature, fix, test and documentation work uses short-lived branches named `feature/<slug>`, `fix/<slug>`, `test/<slug>` and `docs/<slug>`. Commits use Conventional Commit prefixes such as `feat`, `fix`, `test`, `docs`, `refactor` and `chore`.

## Commit Hygiene

The current repository history contains both descriptive Conventional Commits and older generic messages. Examples of clearer wording for future commits:

| Weak message | Clearer Conventional Commit |
|---|---|
| `missing docs added` | `docs(sqe): add missing lab documentation` |
| `fixes` | `fix(library): correct borrowing and inventory behavior` |

These are examples of improved wording, not rewritten historical commits.

## Merge Conflict and Rebase Practice

A real merge-conflict exercise requires two branches that modify the same source line, a PR merge for the first branch, a conflict on the second branch, and a locally recorded resolution. Interactive rebase practice requires a local branch with four commits and an actual `git rebase -i main` operation.

The available GitHub repository records do not contain verifiable screenshots or a local terminal transcript for those operations. No conflict-resolution or interactive-rebase execution is claimed in this document. The actual local terminal output and screenshots should be attached after those exercises are performed.
