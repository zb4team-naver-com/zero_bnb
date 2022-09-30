def mainDir="zerobnb"
def ecrLoginHelper="docker-credential-ecr-login"
def region="us-east-2"
def ecrUrl="809354325893.dkr.ecr.us-east-2.amazonaws.com"
def repository="ecr_test"
def deployHost="172.31.25.231"

pipeline {
    agent any

    tools {
        nodejs 'nodejs-16.17.1'
    }

    stages {
        stage('Pull Codes from Github'){
            steps{
                checkout scm
            }
        }
        
        stage('Build Codes by Gradle') {
            steps {
                sh """
                ./gradlew clean build
                """
            }
        }

        stage('SonarQube analysis') {
                steps {
                    withSonarQubeEnv('Zerobnb-Sonarqube-Server') {
                        sh """
                        ./gradlew sonarqube
                        """
                    }
                }
        }

        stage('Build Docker Image by Jib & Push to AWS ECR Repository') {
            steps {
                withAWS(region:"${region}", credentials:"aws-key") {
                    ecrLogin()
                    sh """
                        curl -O https://amazon-ecr-credential-helper-releases.s3.us-east-2.amazonaws.com/0.4.0/linux-amd64/${ecrLoginHelper}
                        chmod +x ${ecrLoginHelper}
                        mv ${ecrLoginHelper} /usr/local/bin/
                        ./gradlew jib -Djib.to.image=${ecrUrl}/${repository}:${currentBuild.number} -Djib.console='plain'
                    """
                }
            }
        }
        stage('Deploy to AWS EC2 VM'){
            steps{
                sshagent(credentials : ["deploy-key"]) {
                    sh "ssh -o StrictHostKeyChecking=no ec2-user@${deployHost} \
                     'aws ecr get-login-password --region ${region} | sudo docker login --username AWS --password-stdin ${ecrUrl}/${repository}; \
                      sudo docker run -d -p 80:8000 -t ${ecrUrl}/${repository}:${currentBuild.number};'"
                }
            }
        }
    }
}
