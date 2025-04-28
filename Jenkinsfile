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
           stage('Test') {
               steps {
                   sh 'curl -s http://3.15.7.250:8080/hello | grep "Hello, World!"'
               }
           }
       }
   }
