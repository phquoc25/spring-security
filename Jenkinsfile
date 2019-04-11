
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
    }
}