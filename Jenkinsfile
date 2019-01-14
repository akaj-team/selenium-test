pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    node {
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
}
