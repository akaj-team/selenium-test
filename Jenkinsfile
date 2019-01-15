pipeline {
    agent any

    stages {
        stage('Build') {
            echo 'Hello'
        }
        stage('Generate HTML report') {
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
