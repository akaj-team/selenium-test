pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Generate HTML report') {
                cucumber buildStatus: 'UNSTABLE',
                        fileIncludePattern: '**/*.json',
                        trendsLimit: 10,
                        classifications: [
                            [
                                'key': 'Browser',
                                'value': 'Chrome'
                            ]
                        ]
            }
    }
    post {
        success {
              echo "Test succeeded"
              script {
                  cucumber fileIncludePattern: '**/java-calculator/target/cucumber-report.json', sortingMethod: 'ALPHABETICAL'
                    }
        }
        failure {
              echo "Test failed"
              script {
                  cucumber fileIncludePattern: '**/java-calculator/target/cucumber-report.json', sortingMethod: 'ALPHABETICAL'
                     }
        }
    }
}
