pipeline {
    agent any 
    environment {
        DB_LOC = credentials("CB_DB_LOC")
        UTOPIA_DB_UNAME = credentials("CB-DB-USER")
        UTOPIA_DB_PASS = credentials("CB_DB_PASS")
        AWS_CLI = '/usr/local/bin/aws'
    }
    stages {
        stage('init') {
            steps {
                sh 'rm -r userPipeline'
                sh 'git clone https://github.com/Colin-Bradshaw/userPipeline.git'
            }
        }
        stage('build and scan') { 
            steps {
                // booking
                dir ('userPipeline'){
                    withSonarQubeEnv('SonarQubeScanner'){
                        sh 'mvn verify sonar:sonar -Dsonar.host.url=http://jenkins.hitec.link:9000/ -Dsonar.login=dbe54ee91fab45705e2a7ccbdb2a3ef0587cad48'
                    }
                    sh 'mvn clean install > maven-build.txt'
                }
            }
        }
        stage('dockerize') { 
            steps {
                // booking
                dir ('userPipeline'){
                    sh 'docker build -t "jenkins-auto-build-users:latest" .'
                }
                
            }
        }
        stage('push to ecr') { 
            steps {
                withAWS(credentials: 'Jenkins', region: 'us-east-2') {
                    sh '$AWS_CLI ecr get-login-password --region us-east-2 | docker login --username AWS --password-stdin 902316339693.dkr.ecr.us-east-2.amazonaws.com'
                    sh 'docker tag jenkins-auto-build-users:latest 902316339693.dkr.ecr.us-east-2.amazonaws.com/cb-users:jenkins-auto-build-users'
                    sh 'docker push 902316339693.dkr.ecr.us-east-2.amazonaws.com/cb-users:jenkins-auto-build-users'
                }
            }
        }
    }
}
