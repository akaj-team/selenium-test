pipeline {
    agent any

    stages {
        stage("Setup") {
            steps {
                sh "mkdir -p target"
                sh "cd target"
                sh "touch CucumberTestReport.json"
            }
        }
        stage('Build') {
            steps {
                echo 'test'
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
                cucumber fileIncludePattern: 'target/CucumberTestReport.json', sortingMethod: 'ALPHABETICAL'
            }
        }
        failure {
            echo "Test failed"
            cucumber fileIncludePattern: 'target/CucumberTestReport.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
