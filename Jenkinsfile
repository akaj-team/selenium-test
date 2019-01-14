pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clear test'
            }
        }
    }
}