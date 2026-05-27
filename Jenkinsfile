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
        
        choice(
    name: 'SUITE',
    choices: ['smoke', 'regression'],
    description: 'Select Test Suite'
         )

       choice(
    name: 'ENV',
    choices: ['qa', 'uat', 'prod'],
    description: 'Select Environment'
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
                        dir('chrome-run-1') {
                            checkout scm
                            bat """
                            docker run --rm ^
                            -v %cd%:/app ^
                            --shm-size=4g ^
                            qa-automation-framework03dockerheadless ^
                            mvn test -Dbrowser=chrome -Dsuite=${params.SUITE} -Denv=${params.ENV} -Dallure.results.directory=target/allure-results
                            """
                        }
                    }
                }

                stage('Chrome Run 2') {
                    steps {
                        dir('chrome-run-2') {
                            checkout scm
                            bat """
                            docker run --rm ^
                            -v %cd%:/app ^
                            --shm-size=4g ^
                            qa-automation-framework03dockerheadless ^
                           mvn test -Dbrowser=chrome -Dsuite=${params.SUITE} -Denv=${params.ENV} -Dallure.results.directory=target/allure-results
                            """
                        }
                    }
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'screenshots/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'test-output/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'chrome-run-1/target/allure-results/**/*', allowEmptyArchive: true
                archiveArtifacts artifacts: 'chrome-run-2/target/allure-results/**/*', allowEmptyArchive: true
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

            allure([
                includeProperties: false,
                jdk: '',
                results: [
                    [path: 'chrome-run-1/target/allure-results'],
                    [path: 'chrome-run-2/target/allure-results']
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