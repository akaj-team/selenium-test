pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                //  sh 'mvn clean test'
                echo 'build stage'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: 'testtarget/**'
        }

        success {
            echo "Test succeeded"
            script {
                cucumber fileIncludePattern: 'testtarget/*.json', sortingMethod: 'ALPHABETICAL'
            }
        }
        failure {
            echo "Test failed"
            cucumber fileIncludePattern: 'testtarget/*.json', sortingMethod: 'ALPHABETICAL'
        }
    }
}
