pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Unit Test') {
            steps {
                echo 'Testing..'
                sh 'mvn -Dtest=UserServiceTest test'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}