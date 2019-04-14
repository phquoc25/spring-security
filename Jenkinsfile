
pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args '-v /Users/quocphan/.m2:/root/.m2'
        }
    }

    parameters {
            string(name: 'BRANCH_NAME', defaultValue: 'master', description: 'Which branch name would you like to build?')
    }

    stages {
        stage('Checkout') {
                steps {
                    echo "=========Checking out branch ${params.BRANCH_NAME}============="

                    git branch: "${params.BRANCH_NAME}", url: "https://github.com/phquoc25/spring-security.git"
                }
            }

        stage('Build') {
            steps {
                echo "Building ${params.BRANCH_NAME}"
                sh 'mvn -B -DskipTests clean package'
            }
        }

        stage('Deployment') {
            steps {
                sh "docker run -p 2012:3306 --name mysql-qph-docker -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=qph_com -e MYSQL_USER=app_user -e MYSQL_PASSWORD=root mysql:latest"
                sh "docker build -f DockerFile -t qph-app ."
                sh "docker run -t --name qph-app-container --link mysql-qph-docker:mysql -p 8087:8080 qph-app"
            }
        }
    }
}