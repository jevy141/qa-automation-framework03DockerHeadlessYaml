pipeline {
    agent any

    parameters {
        choice(
            name: 'SUITE',
            choices: ['smoke', 'regression', 'all'],
            description: 'Select test suite'
        )
        
         choice(
        name: 'ENV',
        choices: ['qa', 'uat', 'prod'],
        description: 'Select environment'
    )
    }

    stages {
        stage('Docker Compose Build') {
    steps {
        script {
            if (params.SUITE == 'all') {
                sh 'docker compose build smoke-tests regression-tests'
            } else {
                sh "docker compose build ${params.SUITE}-tests"
            }
        }
    }
}

        stage('Docker Test') {
    steps {
        script {
            if (params.SUITE == 'all') {
                parallel(
                    "Smoke Tests": {
                        sh "docker compose run --rm smoke-tests mvn test -Dbrowser=chrome -Dsuite=smoke -Denv=${params.ENV} -Dallure.results.directory=target/allure-results-smoke"
                    },
                    "Regression Tests": {
                        sh "docker compose run --rm regression-tests mvn test -Dbrowser=chrome -Dsuite=regression -Denv=${params.ENV} -Dallure.results.directory=target/allure-results-regression"
                    }
                )
            } else {
                sh "docker compose run --rm ${params.SUITE}-tests mvn test -Dbrowser=chrome -Dsuite=${params.SUITE} -Denv=${params.ENV} -Dallure.results.directory=target/allure-results-${params.SUITE}"
            }
        }
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
			
			script {
            sh '''
                sudo chown -R jenkins:jenkins target reports screenshots || true
                sudo chmod -R 775 target reports screenshots || true
            '''
        }

			
            junit 'target/surefire-reports/junitreports/*.xml'
            
            
            
      
            script {
    if (params.SUITE == 'all') {
        allure([
            includeProperties: false,
            jdk: '',
            results: [
                [path: 'target/allure-results-smoke'],
                [path: 'target/allure-results-regression']
            ]
        ])
    } else {
        allure([
            includeProperties: false,
            jdk: '',
            results: [[path: "target/allure-results-${params.SUITE}"]]
        ])
    }
}

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

archiveArtifacts artifacts: 'screenshots/**/*', allowEmptyArchive: true
archiveArtifacts artifacts: 'reports/**/*', allowEmptyArchive: true
archiveArtifacts artifacts: 'target/surefire-reports/**/*', allowEmptyArchive: true
archiveArtifacts artifacts: 'target/allure-results-*/*', allowEmptyArchive: true


            emailext(
                subject: "Build ${currentBuild.currentResult} - ${params.SUITE} - ${params.ENV}",
                mimeType: 'text/html',
                body: """
                    <h2>Build Status: ${currentBuild.currentResult}</h2>
                    <p>Suite: ${params.SUITE}</p>
                    <p>Environment: ${params.ENV}</p>
                    <p>Job: ${env.JOB_NAME}</p>
                    <p>Build Number: ${env.BUILD_NUMBER}</p>
                    <p><a href="${env.BUILD_URL}">Open Build</a></p>
                """,
                to: "jevy141hanjenkins@gmail.com"
            )
        }
    }
}