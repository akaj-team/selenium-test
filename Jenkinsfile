pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Test') {
            parallel linux: {
                node('linux') {
                  dir("../builds/${BUILD_NUMBER}/") {
                          sh "cp -r cucumber-html-reports $WORKSPACE"
                      }

                  archive "cucumber-html-reports/*"
                }
            }
        }
    }
}
