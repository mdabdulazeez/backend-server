pipeline {
    agent any
    environment {
        PUBLIC_IP = ''
        PRIVATE_IP = ''
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t backend-server .'
            }
        }
        stage('Docker Run') {
            steps {
                sh '''
                    if [ $(docker ps -aq -f name=backend-container) ]; then
                        docker rm -f backend-container
                    fi
                    docker run -d -p 8100:8080 --name backend-container backend-server
                '''
                sh 'sleep 10' // Wait for container to start
            }
        }
        stage('Collect IPs') {
            steps {
                script {
                    env.PUBLIC_IP = sh(script: "curl -s http://checkip.amazonaws.com", returnStdout: true).trim()
                    env.PRIVATE_IP = sh(script: "hostname -I | awk '{print \$1}'", returnStdout: true).trim()
                    echo "Public IP: ${env.PUBLIC_IP}"
                    echo "Private IP: ${env.PRIVATE_IP}"
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    echo "Testing service on public IP..."
                    sh "curl -s http://${env.PUBLIC_IP}:8100/hello | grep 'Hello, World!'"
                    
                }
            }
        }
    }
    post {
        always {
            sh 'docker rm -f backend-container || true'
        }
    }
}
