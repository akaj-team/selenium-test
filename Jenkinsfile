pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Hello'
                sh 'mvn -verion'
            }
        }

        stage('Generate HTML report') {
            steps {
                cucumber buildStatus: 'UNSTABLE',
                    fileIncludePattern: '**/*.json',
                    trendsLimit: 10,
                    classifications: [
                            [
                                    'key'  : 'Browser',
                                    'value': 'Firefox'
                            ]
                    ]
            }
        }
    }
}
