pipeline {
agent any

stages {

    stage('Build & Test') {
        steps {
            bat 'mvn clean test -Dbrowser=chrome'
        }
    }
}

post {
    always {
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
