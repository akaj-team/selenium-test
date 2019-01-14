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
            archiveArtifacts artifacts: 'target/cucumber-reports/**'
        }

        success {
            echo "Test succeeded"
            script {
                cucumber fileIncludePattern: 'target/cucumber-reports/CucumberTestReport.json', sortingMethod: 'ALPHABETICAL'
            }
        }
        failure {
            echo "Test failed"
            cucumber fileIncludePattern: 'target/cucumber-reports/CucumberTestReport.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
