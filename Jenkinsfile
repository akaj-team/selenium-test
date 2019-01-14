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
            archiveArtifacts artifacts: '**/java-calculator/target/**'
        }

        success {
            echo "Test succeeded"
            script {
                cucumber fileIncludePattern: '**/java-calculator/target/cucumber-report.json', sortingMethod: 'ALPHABETICAL'
            }
        }
        failure {
            echo "Test failed"
            cucumber fileIncludePattern: '**/java-calculator/target/cucumber-report.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
