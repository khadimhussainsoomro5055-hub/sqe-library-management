# SQE Library Management System

## Badges

[![Java Tests](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/actions/workflows/build.yml/badge.svg)](https://github.com/khadimhussainsoomro5055-hub/sqe-library-management/actions/workflows/build.yml)

## Project Overview

LibraryHub is a Java 21 Library Management System developed for Software Quality Engineering laboratory work. It supports catalog management, ISBN validation, searching, issuing and returning books, borrowing limits, overdue fine calculations, popularity ranking and catalog export. The project applies black-box testing, boundary-value analysis, JUnit unit testing, test doubles, test-driven development and static-analysis workflow practices.

## Features

- Add books with a unique catalog ID and a valid 13-digit ISBN.
- Reject duplicate catalog IDs and ISBNs.
- Validate book names, authors and non-negative quantities.
- Search titles without case sensitivity.
- Issue available books to members.
- Prevent duplicate concurrent loans of the same book by one member.
- Enforce a maximum of five active loans per member.
- Return only an active loan held by the specified member.
- Calculate overdue fines using the configured rate tiers.
- Rank books by borrowing popularity.
- Calculate member borrowing percentiles.
- Export catalog details through an injectable writer.

## Technologies

- Java 21
- JUnit 5
- Git and GitHub
- GitHub Actions
- SonarCloud (optional repository configuration)

## Project Structure

```text
sqe-library-management/
├── .github/
│   ├── ISSUE_TEMPLATE/
│   ├── workflows/build.yml
│   └── pull_request_template.md
├── docs/
│   ├── requirements.md
│   ├── test-plan.md
│   ├── test-cases.md
│   ├── rtm.md
│   ├── ep-analysis.md
│   ├── bva-analysis.md
│   ├── unit-testing-notes.md
│   ├── tdd-log.md
│   ├── code-review-checklist.md
│   ├── sonar-findings.md
│   └── triage-log.md
├── src/
│   ├── main.java
│   └── books_management/
├── tests/
├── screenshots/
├── CONTRIBUTING.md
├── LICENSE
└── README.md
```

## Requirements

Install JDK 21 and Git.

## Installation

```bash
git clone https://github.com/khadimhussainsoomro5055-hub/sqe-library-management.git
cd sqe-library-management
```

## Run the Application

```bash
mkdir out
javac -d out src/main.java src/books_management/*.java
java -cp out main
```

## Run the Tests

Download the JUnit Platform Console Standalone JAR from Maven Central and save it as `lib/junit-platform-console-standalone.jar`.

Linux/macOS:

```bash
mkdir -p lib out
curl -fL -o lib/junit-platform-console-standalone.jar https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar
javac -cp "lib/junit-platform-console-standalone.jar" -d out src/main.java src/books_management/*.java tests/*.java
java -jar lib/junit-platform-console-standalone.jar execute --class-path out --scan-class-path --details=summary
```

Windows PowerShell:

```powershell
New-Item -ItemType Directory -Force lib,out
Invoke-WebRequest -Uri https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.2/junit-platform-console-standalone-1.10.2.jar -OutFile lib/junit-platform-console-standalone.jar
javac -cp "lib\junit-platform-console-standalone.jar" -d out src\main.java src\books_management\*.java tests\*.java
java -jar lib\junit-platform-console-standalone.jar execute --class-path out --scan-class-path --details=summary
```

## Contribution

Read [CONTRIBUTING.md](CONTRIBUTING.md) for the branch, commit, testing and pull-request conventions.

## License

See [LICENSE](LICENSE).
