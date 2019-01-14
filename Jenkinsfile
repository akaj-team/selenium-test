pipeline {
    agent any

    stages {
        stage("Setup") {
            steps {
                echo 'set up'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
//            archiveArtifacts artifacts: 'target/**'
        }

        success {
            echo "Test succeeded"
            script {
//                cucumber fileIncludePattern: 'target/CucumberTestReport.json', sortingMethod: 'ALPHABETICAL'
            }
        }
        failure {
            echo "Test failed"
//            cucumber fileIncludePattern: 'target/CucumberTestReport.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
