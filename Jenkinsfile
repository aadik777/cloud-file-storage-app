pipeline {
    agent any

    environment {
        IMAGE_NAME = "cloud-storage-backend"
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Backend') {
            steps {
                dir('backend') {
                    bat 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('backend') {
                    bat "docker build -t %IMAGE_NAME% ."
                }
            }
        }

        stage('Deploy') {
            steps {
                bat '''
                docker compose down
                docker compose up -d --build
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                bat 'docker ps'
            }
        }
    }

    post {

        success {
            echo '==========================================='
            echo '✅ CI/CD Pipeline completed successfully!'
            echo '==========================================='
        }

        failure {
            echo '==========================================='
            echo '❌ CI/CD Pipeline failed!'
            echo '==========================================='
        }

        always {
            cleanWs()
        }
    }
}
