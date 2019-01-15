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
        
        stage('Generate HTML report') {
            cucumber buildStatus: 'UNSTABLE',
                    fileIncludePattern: '**/*.json',
                    trendsLimit: 10,
                    classifications: [
                        [
                            'key': 'Browser',
                            'value': 'Firefox'
                        ]
                    ]
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
