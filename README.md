# Jenkins CI/CD Setup

This project is integrated with Jenkins for automated CI/CD execution.

## Features Implemented

* GitHub Webhook Integration
* Dockerized Test Execution
* Parallel Suite Execution
* Smoke & Regression Suite Selection
* QA / UAT / PROD Environment Selection
* Allure Reports
* Extent Reports
* API + UI Integration Testing
* Scheduled Jenkins Builds
* Email Notifications

## Jenkins Pipeline Flow

1. GitHub Push Trigger
2. Jenkins Webhook Trigger
3. Docker Image Build
4. Parallel Test Execution
5. Allure & Extent Report Generation
6. Email Notification

## Technologies Used

* Java
* Selenium
* TestNG
* Maven
* Jenkins
* Docker
* Allure Reports
* Extent Reports
* Rest Assured

## Running Tests via Jenkins

Use **Build With Parameters** in Jenkins:

* Browser: Chrome / Firefox
* Suite: Smoke / Regression
* Environment: QA / UAT / PROD

## Docker Execution

Docker image is built automatically during Jenkins execution.

Example:

```bash
docker build -t qa-automation-framework03dockerheadless .
```

## Webhook Setup

GitHub webhook is connected to Jenkins using ngrok public URL.

## Reports

* Extent Reports generated after execution
* Allure Reports generated and published in Jenkins
