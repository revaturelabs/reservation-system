pipeline {
    agent any
     environment { 
        registry = "nagabhushanamn/web-ui" 
        registryCredential = 'dockerhub' 
        dockerImage = '' 
    }
    stages {
        stage('checkout from scm') {
            steps {
                echo 'checkout..'
            }
        }
        stage('build') {
            steps {
                dir("ui"){
                    sh 'npm i'
                    sh 'npm run build'
                }
            }
        }
        stage('docker:build') {
            steps {
                dir("web-ui"){
                    script { 
                        dockerImage = docker.build registry + ":$BUILD_NUMBER" 
                    }
                }
            }
        }
        stage('docker:push') {
            steps {
                script { 
                    docker.withRegistry('', registryCredential ) { 
                        dockerImage.push() 
                    }
                } 
            }
        }
        stage('Cleaning up') { 
            steps { 
                sh "docker rmi $registry:$BUILD_NUMBER" 
            }
        } 
    }
}