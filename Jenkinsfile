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
                bat "mvn -Dtest=UserServiceTest test"
                junit "**/target/surefire-reports/*.xml"
            }
        }
    }
}