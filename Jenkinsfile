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
                bat "mvn -Dtest=UserServiceTest,UserControllerTest test"
                junit "**/target/surefire-reports/*.xml"
            }
        }
    }
}