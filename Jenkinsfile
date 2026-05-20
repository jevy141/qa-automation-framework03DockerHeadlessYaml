pipeline
{
    agent any

    stages
    {
        stage('Build & Test')
        {
            steps
            {
                bat 'mvn clean test -Dbrowser=chrome'
            }
        }
    }

    post
    {
        always
        {
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'reports',
                reportFiles: 'extent-report.html',
                reportName: 'Extent Report'
            ])
        }
    }
}