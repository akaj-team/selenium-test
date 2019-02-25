pipeline {
    agent any

    stages {
         stage("Parallel") {
             steps {
                 parallel (
                     "firstTask" : {
                         echo "First task"
                         sh 'mvn clean test'
                     },
                     "secondTask" : {
                         echo "Second task"
                         sh 'mvn clean test'
                     }
                 )
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
