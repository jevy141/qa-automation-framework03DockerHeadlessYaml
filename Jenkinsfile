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
                bat 'docker build -t qa-automation-framework03dockerheadless .'
            }
        }

        stage('Docker Run') {
    steps {
        bat """
        docker run --rm ^
        -v %WORKSPACE%:/app ^
        --shm-size=2g ^
        qa-automation-framework03dockerheadless ^
        mvn clean test -Dbrowser=${params.BROWSER}
        """
    }
}

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'screenshots/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
            }
        }

        stage('Docker Cleanup') {
            steps {
                bat 'docker system prune -af'
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
            // also add plgin and toll in jenkins 
            allure([
            includeProperties: false,
            jdk: '',
            results: [[path: 'target/allure-results']]
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