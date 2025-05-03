# Backend Server SonarQube Analysis

This guide explains how to run SonarQube analysis on this Maven Spring Boot application.

## Prerequisites

1. SonarQube server running (default: http://localhost:9000)
2. SonarQube token generated from your SonarQube server
3. Java and Maven installed

## Running SonarQube Analysis

1. Export your SonarQube token as an environment variable:

```bash
export SONAR_TOKEN=your-sonarqube-token
```

2. Run Maven with SonarQube analysis:

```bash
mvn clean verify sonar:sonar
```

3. View the results in your SonarQube dashboard at http://localhost:9000

## Understanding the Results

The SonarQube analysis will check your code against the default Java Quality Profile ("Sonar way"), which includes:

- Bug detection
- Code smells (maintainability issues)
- Vulnerability detection
- Security hotspots
- Code coverage thresholds

### Viewing Passed and Failed Rules

In the SonarQube dashboard:

1. Go to the "Issues" tab to see all detected issues
2. Use filters to narrow down by type, severity, and status
3. Check the "Quality Gate" section to see overall pass/fail status

## Customizing Quality Profiles

If needed, you can create a custom Quality Profile:

1. Go to Quality Profiles > Java > Copy the "Sonar way" profile
2. Customize the rules based on your team's standards
3. Set your custom profile as default for this project

## Continuous Integration

For CI/CD pipelines, add the following environment variables to your pipeline configuration:

- `SONAR_TOKEN`: Your SonarQube token
- `SONAR_HOST_URL`: URL of your SonarQube server (if different from localhost) 