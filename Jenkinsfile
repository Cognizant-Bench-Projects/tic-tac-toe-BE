pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'make'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        stage('Test') {
            steps {
                sh "mvn package -Dmaven.test.skip=false"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
        post{
            always{
                script{
                    junit "**/target/surefire-reports/*.xml"
                }
            }
        }
    }
}