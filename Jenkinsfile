pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'aaa'
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
                cucumber fileIncludePattern: 'target/cucumber-parallel/*.json', sortingMethod: 'ALPHABETICAL'
            }
        }
        failure {
            echo "Test failed"
            cucumber fileIncludePattern: 'target/cucumber-parallel/*.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
