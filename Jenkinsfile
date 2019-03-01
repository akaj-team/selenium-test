pipeline {
    agent any

    stages {
        stage('Run Chrome Test') {
            steps {
                sh 'run-test.sh chrome 3'
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
                cucumber fileIncludePattern: 'target/cucumber-reports/*.json', sortingMethod: 'ALPHABETICAL'
            }
        }
        failure {
            echo "Test failed"
            cucumber fileIncludePattern: 'target/cucumber-reports/*.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
