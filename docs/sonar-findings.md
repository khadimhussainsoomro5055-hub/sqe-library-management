# SonarCloud Findings — SQE Library Management

## Analysis Information
- Project: SQE Library Management
- Repository: khadimhussainsoomro5055-hub/sqe-library-management
- Language: Java 21
- Tool: SonarCloud

## Confirmed Finding in the Available Scan Notes

### java:S2187 — Test class should contain tests

| Field | Details |
|---|---|
| File | `tests/MemberTest.java` |
| Reported severity | Blocker |
| Status | Fixed in source |
| Resolution | Added JUnit tests for middle, lowest, highest and single-member percentile results, plus empty and null library validation. |

The test class now contains executable JUnit tests. The current regression suite also checks tied percentile values and invalid member input.

## Analyzer Configuration
The workflow downloads the JUnit Platform Console Standalone JAR and compiles the application and test sources. The SonarCloud scan job runs only when the repository variable `SONAR_ENABLED` is set to `true` and uses the `SONAR_TOKEN` secret. The scanner configuration points to Java source and test directories and supplies the JUnit library path.

## Findings Register
The available repository notes establish one specific Sonar rule finding. They do not provide evidence for eight distinct findings, so additional findings are not invented here. Any new issues must be copied from the actual SonarCloud Issues tab with rule, file, line, severity and explanation.

## Quality Gate Evidence
A passing test workflow is not the same as a passing SonarCloud Quality Gate. The Quality Gate status and before/after dashboard screenshots must be captured from the actual SonarCloud analysis once the token and project analysis are available.
