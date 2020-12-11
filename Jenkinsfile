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
                bat "mvn package -Dmaven.test.skip=false"
                junit "**/target/surefire-reports/*.xml"
            }
        }
    }
}