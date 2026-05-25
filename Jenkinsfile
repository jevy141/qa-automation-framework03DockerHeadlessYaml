pipeline {
    agent any

parameters {
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Select Browser'
        )
    }
    stages {

        stage('Docker Build') {
            steps {
                bat 'docker build -t qa-auutomation-framework03dockerheadless .'
            }
        }

        stage('Docker Run') {
            steps {
                bat 'docker run --rm qa-auutomation-framework03dockerheadless  mvn test -Dbrowser=${params.BROWSER}' //This automatically removes stopped containers.
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