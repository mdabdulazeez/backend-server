pipeline {
    agent any
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/mdabdulazeez/backend-server.git'
            }
        }
        stage('Build and Deploy') {
            steps {
                withMaven(
                    maven: 'Maven',
                    mavenSettingsConfig: 'nexus-settings'
                ) {
                    sh 'mvn deploy'
                }
            }
        }
    }
}
