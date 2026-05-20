pipeline {
    agent any

    stages {

        stage('Build & Test') {
            steps {
                bat 'mvn clean test -Dbrowser=chrome'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'screenshots/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
            }
        }
    }

    post {

        always {

            publishHTML([
                reportDir: 'reports',
                reportFiles: 'ExtentReport.html',
                reportName: 'Extent Report',
                keepAll: true,
                alwaysLinkToLastBuild: true,
                allowMissing: true
            ])

            emailext(
                subject: "Jenkins Build: ${currentBuild.currentResult}",
                body: """
                    Build Status: ${currentBuild.currentResult}

                    Project: ${env.JOB_NAME}
                    Build Number: ${env.BUILD_NUMBER}

                    Check console output:
                    ${env.BUILD_URL}
                """,
                to: "jevy141hanjenkins@gmail.com"
            )
        }
    }
}