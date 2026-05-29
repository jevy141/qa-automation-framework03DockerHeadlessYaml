pipeline {
    agent any

    stages {
        stage('Docker Compose Build') {
            steps {
                sh 'docker compose build smoke-tests'
            }
        }

        stage('Docker Smoke Test') {
            steps {
                sh 'docker compose run --rm smoke-tests'
            }
        }

        stage('Fix Permissions') {
            steps {
                sh 'sudo chown -R jenkins:jenkins target || true'
                sh 'sudo chmod -R 775 target || true'
            }
        }

        stage('Docker Compose Down') {
            steps {
                sh 'docker compose down --remove-orphans'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/junitreports/*.xml'

            allure([
                includeProperties: false,
                jdk: '',
                results: [[path: 'target/allure-results-smoke']]
            ])

            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/surefire-reports',
                reportFiles: 'index.html',
                reportName: 'TestNG Report'
            ])

            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'extent-report.html',
                reportName: 'Extent Report'
            ])

            emailext(
                subject: "Build ${currentBuild.currentResult} - ${env.JOB_NAME}",
                mimeType: 'text/html',
                body: """
                    <h2>Build Status: ${currentBuild.currentResult}</h2>
                    <p>Job: ${env.JOB_NAME}</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p><a href="${env.BUILD_URL}">Open Build</a></p>
                """,
                to: "jevy141hanjenkins@gmail.com"
            )
        }
    }
}