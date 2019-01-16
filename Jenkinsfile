pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean test -Dcucumber.options="src/test/resources/features/login/Authenticate.feature"'
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