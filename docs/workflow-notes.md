# Development Workflow Notes

## Change Flow

```text
Idea → Issue → Branch → Pull Request → Review → Merge → CI → Release
```

QA clarifies acceptance criteria at the issue stage, identifies tests before implementation, checks the code and tests during review, verifies the CI result after merge, and performs smoke/regression checks before a release.

## Branch and Commit Conventions

Feature, fix, test and documentation work uses short-lived branches named `feature/<slug>`, `fix/<slug>`, `test/<slug>` and `docs/<slug>`. Commits use Conventional Commit prefixes such as `feat`, `fix`, `test`, `docs`, `refactor` and `chore`.

## Verified review evidence — Lab 9

Pull request [#23](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/pull/23) contains six inline review threads. The repository review API confirms all six threads are resolved. The history contains a Changes Requested review followed by an approval. The comments addressed floating-point percentile calculation, empty-library handling, assertion-based tests, JavaDoc and single-member behavior.

## Verified TDD evidence — Lab 8

The following commit pairs in the repository history establish Red/Green cycles for the popularity-ranking feature:

| Cycle | RED test commit | GREEN implementation commit |
|---|---|---|
| Empty catalog | [6dd187e](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/commit/6dd187e2c260a3fc53ef90c8ed53bc76056f8533) | [ec07daa](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/commit/ec07daaac038473d2cb438da604438596f9096e9) |
| Single book | [33a5afc](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/commit/33a5afcd0d02c0a32e3f06707153fd19c576948a) | [dccd1f0](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/commit/dccd1f0462b0cc0496cbeedf87b69d2a2e69d9c5) |
| Popularity sorting | [f8c9ef1](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/commit/f8c9ef1c0c8b8323475457bbf51bd29b255c2a09) | [8020f87](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/commit/8020f8797fab9eed19bbb43969df249c84893df9) |

A refactor commit follows the third cycle. The tie-breaking and never-borrowed tests passed immediately because those behaviors were already implemented; they are recorded as verification tests, not fabricated Red cycles. See `docs/tdd-log.md`.

## Commit Hygiene

The current repository history contains both descriptive Conventional Commits and older generic messages. Examples of clearer wording for future commits:

| Weak message | Clearer Conventional Commit |
|---|---|
| `missing docs added` | `docs(sqe): add missing lab documentation` |
| `fixes` | `fix(library): correct borrowing and inventory behavior` |

These are examples of improved wording, not rewritten historical commits.

## Merge Conflict and Interactive Rebase Practice

A real merge-conflict exercise requires two branches that modify the same source line, a PR merge for the first branch, a conflict on the second branch, and a locally recorded resolution. Interactive rebase practice requires a local branch with four commits and an actual `git rebase -i main` operation.

The available repository records do not contain verifiable terminal output or screenshots for those operations. No conflict-resolution or interactive-rebase execution is claimed here. The actual local terminal output and screenshots must be attached after those exercises are performed.

## Repository Settings and Project Board

The Lab 1 handout also requires branch protection on `main` (PR required, one approval and status checks), squash-only merging, and a GitHub Projects board named `SQE Lab Board` with Backlog, In Progress, In Review and Done columns.

These settings and the live Project board are not exposed through the connected repository actions used for this audit. They remain unverified and should be checked in GitHub Settings and Projects. Attach screenshots of the actual settings and board rather than recording assumed configuration.
