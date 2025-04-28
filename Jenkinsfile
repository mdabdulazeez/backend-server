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
                   sh 'docker run -d -p 8100:8080 --name backend-container backend-server'
               }
           }
           stage('Test') {
               steps {
                   sh 'curl -s http://3.15.7.250:8100/hello | grep "Hello, World!"'
               }
           }
       }
   }
