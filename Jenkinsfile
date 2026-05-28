pipeline {

    agent any

    triggers {
        githubPush()
        cron('H H * * 0')
    }

    options {
        buildDiscarder(logRotator(
            numToKeepStr: '10',
            artifactNumToKeepStr: '5'
        ))
    }

    stages {

        stage('Docker Compose Build') {
            steps {
                bat 'docker compose build'
            }
        }

        stage('Docker Compose Run') {
    parallel {

        stage('Smoke Compose') {
            steps {
                bat 'docker compose run --rm smoke-tests'
            }
        }

        stage('Regression Compose') {
            steps {
                bat 'docker compose run --rm regression-tests'
            }
        }
    }
}

        stage('Docker Compose Down') {
            steps {
                bat 'docker compose down'
            }
        }
    }

    post {

        always {

            archiveArtifacts artifacts: 'target/allure-results-smoke/**/*', allowEmptyArchive: true
            archiveArtifacts artifacts: 'target/allure-results-regression/**/*', allowEmptyArchive: true
            archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
            archiveArtifacts artifacts: 'screenshots/**/*', allowEmptyArchive: true
            archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true

            allure([
                includeProperties: false,
                jdk: '',
                results: [
                    [path: 'target/allure-results-smoke'],
                    [path: 'target/allure-results-regression']
                ]
            ])

            emailext(
                subject: "Docker Compose Jenkins Build: ${currentBuild.currentResult}",
                body: """
Build Status: ${currentBuild.currentResult}

Project: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}

Console:
${env.BUILD_URL}
""",
                to: "jevy141hanjenkins@gmail.com"
            )
        }
    }
}