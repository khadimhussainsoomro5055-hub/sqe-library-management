# SonarCloud Findings — SQE Library Management

## Analysis information

- Project: SQE Library Management
- Repository: khadimhussainsoomro5055-hub/sqe-library-management
- Language: Java 21
- Tool: SonarCloud

## Confirmed historical finding

### java:S2187 — Test class should contain tests

| Field | Details |
|---|---|
| File | `tests/MemberTest.java` |
| Reported severity | Blocker (as recorded in the existing project notes) |
| Status | Fixed in source |
| Resolution | Added executable JUnit tests for middle, lowest, highest and single-member percentile results, plus empty/null library validation. |

The test class now contains executable tests. Regression coverage also checks tied percentile values and invalid member input.

## Analyzer configuration

The repository contains `sonar-project.properties` configured for Java 21:

- Production sources: `src`
- Test sources: `tests`
- Production bytecode: `out/main`
- Test bytecode: `out/test`
- JaCoCo XML: `coverage/jacoco.xml`

The GitHub Actions workflow compiles production and test classes separately, executes JUnit with JaCoCo, generates the XML report, and passes it to the SonarCloud scanner.

## Current scan status

The SonarCloud job is intentionally conditional on repository configuration:

- Repository variable: `SONAR_ENABLED=true`
- Repository Actions secret: `SONAR_TOKEN` (create this in GitHub Settings → Secrets and variables → Actions)

The latest GitHub Actions runs show the SonarCloud job as **skipped**, because these repository settings are not enabled/available to the workflow. Therefore, this repository does not yet have a verified current SonarCloud dashboard, current issue list, or Quality Gate result.

Do not interpret a passing JUnit/JaCoCo job as a SonarCloud Quality Gate pass.

## Findings register

The existing project notes record one historical rule finding (java:S2187). No current SonarCloud issue export is available while the scan is skipped, so additional rule IDs, file/line locations, severities, and risks are not fabricated. After enabling the scan, copy the actual top eight findings from the SonarCloud Issues tab into this table.

| Rule | File and line | Severity | Risk / explanation | Status |
|---|---|---|---|---|
| java:S2187 | `tests/MemberTest.java` | Blocker (historical note) | A test class without tests provides no executable regression protection. | Fixed in source |
| Pending live scan | — | — | Populate from SonarCloud Issues after the first analysis. | Pending |

## Lab 10 completion checklist

- [x] Commit `sonar-project.properties`.
- [x] Configure the GitHub Actions SonarCloud scan job.
- [x] Configure JaCoCo XML import for Java coverage.
- [x] Fix the previously recorded java:S2187 finding in source.
- [ ] Enable `SONAR_ENABLED` and add `SONAR_TOKEN` in repository settings.
- [ ] Run the first real SonarCloud analysis and capture the dashboard overview.
- [ ] Triage the actual top eight findings by rule, file/line, severity and risk.
- [ ] Resolve all reported bugs/vulnerabilities and at least five code smells where the live scan identifies them.
- [ ] Re-run SonarCloud and confirm the Quality Gate passes.
- [ ] Capture before/after dashboard screenshots and link fix PRs.

The remaining unchecked items require the repository owner to configure the SonarCloud project/token and require results from the actual SonarCloud service. No dashboard screenshots or Quality Gate result are claimed before that scan exists.
