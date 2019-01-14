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
            //node('node1'){
    echo "Test succeeded"
                script {
        // configured from using gmail smtp Manage Jenkins-> Configure System -> Email Notification
        // SMTP server: smtp.gmail.com
        // Advanced: Gmail user and pass, use SSL and SMTP Port 465
        // Capitalized variables are Jenkins variables – see https://wiki.jenkins.io/display/JENKINS/Building+a+software+project
                    mail(bcc: '',
                         body: "Run ${JOB_NAME}-#${BUILD_NUMBER} succeeded. To get more details, visit the build results page: ${BUILD_URL}.",
                         cc: '',
                         from: 'jenkins-admin@gmail.com',
                         replyTo: '',
                         subject: "${JOB_NAME} ${BUILD_NUMBER} succeeded",
                         to: env.notification_email)
                         if (env.archive_war =='yes')
                         {
                 // ArchiveArtifact plugin
                            archiveArtifacts '**/java-calculator-*-SNAPSHOT.jar'
                          }
                           // Cucumber report plugin
                          cucumber fileIncludePattern: '**/java-calculator/target/cucumber-report.json', sortingMethod: 'ALPHABETICAL'
                //publishHTML([allowMissing: false, alwaysLinkToLastBuild: false, keepAll: true, reportDir: '/home/reports', reportFiles: 'reports.html', reportName: 'Performance Test Report', reportTitles: ''])
                }
            //}
            }
            failure {
                echo "Test failed"
                mail(bcc: '',
                    body: "Run ${JOB_NAME}-#${BUILD_NUMBER} succeeded. To get more details, visit the build results page: ${BUILD_URL}.",
                     cc: '',
                     from: 'jenkins-admin@gmail.com',
                     replyTo: '',
                     subject: "${JOB_NAME} ${BUILD_NUMBER} failed",
                     to: env.notification_email)
                     cucumber fileIncludePattern: '**/java-calculator/target/cucumber-report.json', sortingMethod: 'ALPHABETICAL'
    //publishHTML([allowMissing: true, alwaysLinkToLastBuild: false, keepAll: true, reportDir: '/home/tester/reports', reportFiles: 'reports.html', reportName: 'Performance Test Report', reportTitles: ''])
            }
        }
}
