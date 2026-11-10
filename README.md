# SQE-Library_Management-System

## Badges

<!-- CI badge will be added in Lab 14 -->

## Project Overview

LibraryHub is a Java-based Library Management System developed as part of the Software Quality Engineering (SQE) laboratory work.

The project provides basic library operations, including adding books, searching books, issuing books, returning books, and managing book availability. It also demonstrates software testing techniques such as unit testing, equivalence partitioning, boundary value analysis, and test-driven development.

## Features

- Add new books to the library.
- Display all available books.
- Search books by title.
- Issue books to library members.
- Return issued books.
- Prevent duplicate book IDs.
- Validate book quantities.
- Enforce a borrowing limit of five books per member.
- Calculate overdue fines.
- Validate ISBN numbers.
- Rank books according to borrowing popularity.
- Export library catalog information.

## Technologies Used

- Java 21
- JUnit 5
- Git
- GitHub

## Project Structure

```text
sqe-library-management/
│
├── .github/
│   ├── ISSUE_TEMPLATE/
│   │   ├── bug_report.md
│   │   └── feature_request.md
│   └── pull_request_template.md
│
├── docs/
│   ├── requirements.md
│   ├── test-plan.md
│   ├── test-cases.md
│   ├── rtm.md
│   ├── ep-analysis.md
│   ├── bva-analysis.md
│   ├── triage-log.md
│   ├── tdd-log.md
│   └── code-review-checklist.md
│
├── src/
│   ├── main.java
│   └── books_management/
│
├── tests/
│   └── JUnit test files
│
├── screenshots/
├── LICENSE
└── README.md
```

## Requirements

Before running this project, make sure you have installed:

1. Java Development Kit (JDK) 21.
2. Git.
3. JUnit 5 for running automated tests.

## Installation

Clone the repository:

```bash
git clone https://github.com/khadimhussainsoomro5055-hub/sqe-library-management.git
```

Move into the project directory:

```bash
cd sqe-library-management
```

## Running the Application

Compile the Java source files:

```bash
javac -d out src/main.java src/books_management/*.java
```

Run the application:

```bash
java -cp out main
```

## Running Tests

This project uses JUnit 5 for automated testing.

Download the JUnit Platform Console Standalone JAR and place it inside a `lib` directory in the project root.

Compile the application and test files:

```bash
javac -cp "lib/junit-platform-console-standalone.jar" -d out src/main.java src/books_management/*.java tests/*.java
```

Run all tests:

```bash
java -jar lib/junit-platform-console-standalone.jar execute --class-path out --scan-class-path
```

Run tests with detailed output:

```bash
java -jar lib/junit-platform-console-standalone.jar execute --class-path out --scan-class-path --details=tree
```

Run tests with summary output:

```bash
java -jar lib/junit-platform-console-standalone.jar execute --class-path out --scan-class-path --details=summary
```

## Documentation

Additional project documentation is available in the `docs/` directory.

It includes:

- Project requirements
- Test plan
- Test cases
- Requirements Traceability Matrix (RTM)
- Equivalence Partitioning analysis
- Boundary Value Analysis
- Defect triage log
- TDD development log
- Code review checklist

## Contribution

Contributions are welcome. Please read [CONTRIBUTING.md](CONTRIBUTING.md) before making changes or submitting a pull request.

## License

This project is licensed under the terms provided in the LICENSE file.
