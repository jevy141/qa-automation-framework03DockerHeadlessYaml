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
    subject: "Build ${currentBuild.currentResult} - ${env.JOB_NAME}",
    mimeType: 'text/html',
    body: """
    <h2>Build Status: ${currentBuild.currentResult}</h2>
    <p>Job: ${env.JOB_NAME}</p>
    <p>Build Number: ${env.BUILD_NUMBER}</p>

    <p>
      <a href="${env.BUILD_URL}">
        Open Build
      </a>
    </p>
    """,
    to: "jevy141hanjenkins@gmail.com"
)
        }
    }
}
// using webhook 