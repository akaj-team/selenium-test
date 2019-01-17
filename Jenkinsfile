pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'build'
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'target/**'
        }

        success {
            echo "Test succeeded"
            script {
                cucumber fileIncludePattern: 'target/*.json', sortingMethod: 'ALPHABETICAL'
            }
        }
        failure {
            echo "Test failed"
            cucumber fileIncludePattern: 'target/*.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
