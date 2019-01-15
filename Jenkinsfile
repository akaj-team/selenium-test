pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'test'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'targetx/**'
        }

        success {
            echo "Test succeeded"
            script {
                cucumber fileIncludePattern: 'targetx/cucumber-report.json', sortingMethod: 'ALPHABETICAL'
            }
        }
        failure {
            echo "Test failed"
            cucumber fileIncludePattern: 'targetx/cucumber-report.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
