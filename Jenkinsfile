pipeline {

    agent any

    stages {

        stage('Checkout') {
            steps {
                git 'https://github.com/jevy141/qa-automation-framework2.git'
            }
        }

        stage('Build & Test') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}