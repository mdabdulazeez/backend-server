# Error-Filled Backend Server

This project is a demonstration of a Spring Boot application containing numerous intentional code quality issues, vulnerabilities, and bugs designed to trigger SonarQube analysis failures.

## Purpose

The purpose of this project is to demonstrate how SonarQube identifies and reports various code quality issues. It contains multiple examples of:

- Security vulnerabilities (SQL injection, XSS, hardcoded credentials)
- Code smells (duplicated code, complex methods, unclosed resources)
- Bugs (empty catch blocks, resource leaks)
- Test gaps (lack of test coverage)
- Deprecated API usage

## How to Use

1. Ensure you have a SonarQube instance running (locally or remotely)
2. Set your SonarQube token:

```bash
export SONAR_TOKEN=your_sonar_token
```

3. Run Maven build with SonarQube analysis:

```bash
mvn clean verify sonar:sonar
```

4. Check the SonarQube dashboard to see all the identified issues

## Project Structure

The project contains intentional errors in the following categories:

- **Security Issues**: Authentication, cryptography, input validation
- **Code Quality**: Complex methods, duplications, unused variables
- **Resource Management**: Unclosed connections and streams
- **API Usage**: Deprecated methods and improper exceptions
- **Design Issues**: Poor encapsulation, excessive complexity

## Notes

- This project is for demonstration purposes only and should not be used as a reference for proper coding practices
- The build is configured to fail if quality gates are not met (`sonar.qualitygate.wait=true`)
- All errors are intentional and designed to show different types of issues SonarQube can detect 