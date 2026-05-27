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

        stage('Parallel Execution') {
    parallel {

        stage('Chrome Run 1') {
            steps {
                bat """
                docker run --rm ^
                -v %WORKSPACE%:/app ^
                --shm-size=2g ^
                qa-automation-framework03dockerheadless ^
                mvn test -Dbrowser=chrome -Dallure.results.directory=target/allure-results-chrome1
                """
            }
        }

        stage('Chrome Run 2') {
            steps {
                bat """
                docker run --rm ^
                -v %WORKSPACE%:/app ^
                --shm-size=2g ^
                qa-automation-framework03dockerheadless ^
                mvn test -Dbrowser=chrome -Dallure.results.directory=target/allure-results-chrome2
                """
            }
        }
    }
}

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'screenshots/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'target/allure-results-chrome1/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'target/allure-results-chrome2/**/*', allowEmptyArchive: true
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
    results: [
        [path: 'target/allure-results-chrome1'],
        [path: 'target/allure-results-chrome2']
    ]
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