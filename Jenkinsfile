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
                sh 'docker build -t localhost:5000/backend-server .'
            }
        }
        stage('Nexus') {
            steps {
                sh '''
                    # Start local registry if not running
                    if [ -z "$(docker ps -q -f name=registry)" ]; then
                        docker run -d -p 5000:5000 --restart=always --name registry registry:2
                        sleep 5
                    fi
                    
                    # Push to local registry
                    docker push localhost:5000/backend-server
                    
                    # Save image version
                    docker tag localhost:5000/backend-server localhost:5000/backend-server:${BUILD_NUMBER}
                    docker push localhost:5000/backend-server:${BUILD_NUMBER}
                '''
            }
        }
        stage('Docker Run') {
            steps {
                sh '''
                    if [ $(docker ps -aq -f name=backend-container) ]; then
                        docker rm -f backend-container
                    fi
                    docker run -d -p 8100:8080 --name backend-container localhost:5000/backend-server
                '''
                sh 'sleep 10'
            }
        }
        stage('Test') {
            steps {
                script {
                    def publicIp = sh(script: "curl -s http://checkip.amazonaws.com", returnStdout: true).trim()
                    def privateIp = sh(script: "hostname -I | awk '{print \$1}'", returnStdout: true).trim()
                    
                    echo "Public IP: ${publicIp}"
                    echo "Private IP: ${privateIp}"
                    
                    // Test using both IPs
                    sh "curl -s http://${publicIp}:8100/hello | grep 'Hello, World!'"
                    sh "curl -s http://${privateIp}:8100/hello | grep 'Hello, World!'"
                }
            }
        }
    }
    post {
        always {
            sh 'docker rm -f backend-container || true'
            // Keep registry running between builds
            sh 'docker rm -f registry || true'  // Remove if you want persistent registry
        }
    }
}
