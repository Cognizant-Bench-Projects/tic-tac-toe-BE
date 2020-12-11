pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                junit "**/target/surefire-reports/*.xml"
            }
        }
    }
}