pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
               // sh 'mvn clean test'
                echo 'test'
                sh 'mvn -version'
            }
        }
    }

//    post {
//        always {
////            archiveArtifacts artifacts: 'target/**'
//        }
//
//        success {
//            echo "Test succeeded"
//            script {
////                cucumber fileIncludePattern: 'target/CucumberTestReport.json', sortingMethod: 'ALPHABETICAL'
//            }
//        }
//        failure {
//            echo "Test failed"
////            cucumber fileIncludePattern: 'target/CucumberTestReport.json', sortingMethod: 'ALPHABETICAL'
//        }
//    }
}
