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

            archiveArtifacts artifacts: 'reports/**/*', fingerprint: true

            archiveArtifacts artifacts: 'screenshots/**/*', allowEmptyArchive: true

            archiveArtifacts artifacts: 'test-output/**/*', fingerprint: true
        }
    }
}

post {

    always {

        publishHTML([
            allowMissing: true,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: 'reports',
            reportFiles: 'ExtentReport.html',
            reportName: 'Extent Report'
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
