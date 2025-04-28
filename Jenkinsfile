pipeline {
    agent any
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
        stage('Test') {
            steps {
                script {
                    // Get IPs
                    def publicIp = sh(script: "curl -s http://checkip.amazonaws.com", returnStdout: true).trim()
                    def privateIp = sh(script: "hostname -I | awk '{print \$1}'", returnStdout: true).trim()
                    
                    echo "Public IP: ${publicIp}"
                    echo "Private IP: ${privateIp}"
                    
                    // Test using public IP
                    echo "Testing service on public IP..."
                    sh "curl -s http://${publicIp}:8100/hello | grep 'Hello, World!'"
                    
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
// 
