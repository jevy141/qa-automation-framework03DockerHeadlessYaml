# Selenium Cloud-Based QA Automation Framework

## Overview

This project is an end-to-end QA Automation Framework built using Selenium WebDriver, Java, TestNG, Maven, Jenkins, Docker, GitHub, Rest Assured, and AWS EC2.

The framework follows the Page Object Model (POM) design pattern and supports UI Automation, API Automation, CI/CD integration, Dockerized execution, reporting, parallel execution, and cloud-based deployment.

---

## Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* Rest Assured
* Jenkins
* Docker
* Docker Compose
* Git & GitHub
* GitHub Webhooks
* AWS EC2
* Allure Reports
* Extent Reports

---

## Framework Features

* Page Object Model (POM)
* UI & API Automation Testing
* Smoke & Regression Test Suites
* Environment-Based Execution (QA/UAT/PROD)
* Screenshot Capture on Failure
* Allure Reports
* Extent Reports
* TestNG Reports
* Artifact Archiving
* Parallel Suite Execution
* Dockerized Test Execution
* Jenkins CI/CD Integration
* GitHub Webhook Integration
* Email Notifications

---

## Jenkins CI/CD Setup

This project is integrated with Jenkins for automated CI/CD execution.

### Features Implemented

* GitHub Webhook Integration
* Parameterized Jenkins Pipelines
* Dockerized Test Execution
* Smoke & Regression Suite Selection
* QA / UAT / PROD Environment Selection
* Parallel Suite Execution
* Allure Report Publishing
* Extent Report Publishing
* API + UI Integration Testing
* Scheduled Jenkins Builds
* Email Notifications
* Artifact Archiving

---

## Jenkins Pipeline Flow

```text
GitHub Push
      │
      ▼
GitHub Webhook Trigger
      │
      ▼
Jenkins Pipeline
      │
      ▼
Docker Image Build
      │
      ▼
Smoke / Regression Execution
      │
      ▼
Parallel Execution (Optional)
      │
      ▼
Allure & Extent Report Generation
      │
      ▼
Email Notification
```

---

## Running Tests via Jenkins

Use **Build With Parameters**:

```text
SUITE       : Smoke / Regression / All
ENVIRONMENT : QA / UAT / PROD
```

---

## Running Tests Locally

```bash
mvn clean test -Dbrowser=chrome -Dsuite=smoke -Denv=qa
```

---

## Docker Execution

Build Docker Image:

```bash
docker compose build smoke-tests
```

Run Smoke Suite:

```bash
docker compose run --rm smoke-tests
```

Run Regression Suite:

```bash
docker compose run --rm regression-tests
```

---

## AWS Deployment

* Jenkins deployed on AWS EC2
* Docker & Docker Compose installed on EC2
* GitHub integrated using Webhooks
* Test execution performed on EC2 Linux environment

---

## Reports

### Allure Reports

* Generated after execution
* Published automatically in Jenkins

### Extent Reports

* Generated after execution
* Archived as Jenkins artifacts

### TestNG Reports

* Generated after every execution
* Published through Jenkins

---

## Webhook Setup

GitHub repository is integrated with Jenkins using GitHub Webhooks for automatic pipeline triggering on every code push.

---

## Project Repository

GitHub:
https://github.com/jevy141/qa-automation-framework03DockerHeadlessYaml

---

## Author

Sohan Rana
