pipeline {
    agent any

    tools {
        maven 'Maven3'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/jevy141/qa-automation-framework03DockerHeadlessYaml.git'
            }
        }

        stage('Run Smoke Tests') {
            steps {
                sh 'mvn clean test -Dsuite=smoke'
            }
        }
    }

    post {
        always {
            junit 'target/surefire-reports/junitreports/*.xml'

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
    subject: "Jenkins Build ${currentBuild.currentResult}: ${env.JOB_NAME}",
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
// using webhook 