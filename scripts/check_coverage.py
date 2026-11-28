#!/usr/bin/env python3
"""Fail CI when the JaCoCo aggregate line coverage is below the configured minimum."""
import sys
import xml.etree.ElementTree as ET
from pathlib import Path


def main() -> int:
    if len(sys.argv) != 3:
        print("Usage: check_coverage.py <jacoco.xml> <minimum-line-percent>")
        return 2

    report = Path(sys.argv[1])
    minimum = float(sys.argv[2])
    if not report.is_file():
        print(f"Coverage report not found: {report}")
        return 2

    root = ET.parse(report).getroot()
    line_counter = next(
        (counter for counter in root.findall("counter")
         if counter.attrib.get("type") == "LINE"),
        None,
    )
    if line_counter is None:
        print("JaCoCo report does not contain an aggregate LINE counter.")
        return 2

    covered = int(line_counter.attrib["covered"])
    missed = int(line_counter.attrib["missed"])
    total = covered + missed
    percentage = (covered / total * 100.0) if total else 100.0

    branch_counter = next(
        (counter for counter in root.findall("counter")
         if counter.attrib.get("type") == "BRANCH"),
        None,
    )
    if branch_counter is not None:
        branch_covered = int(branch_counter.attrib["covered"])
        branch_missed = int(branch_counter.attrib["missed"])
        branch_total = branch_covered + branch_missed
        branch_percentage = (
            branch_covered / branch_total * 100.0 if branch_total else 100.0
        )
        print(
            f"Branch coverage: {branch_percentage:.2f}% "
            f"({branch_covered}/{branch_total})"
        )

    print(f"Line coverage: {percentage:.2f}% ({covered}/{total}); minimum: {minimum:.2f}%")
    if percentage + 1e-9 < minimum:
        print("Coverage threshold not met.")
        return 1

    print("Coverage threshold met.")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
