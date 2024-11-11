pipeline{
    agent any
    environment{
        DOCKERHUB_CREDENTIALS = credentials('ggnagpae1-dockerhub')
    }

    stages{
        stage('Set Variables') {
            steps {
                echo 'Set Variables'
                script {

                    // DOCKER
                    DOCKER_HUB_URL = 'registry.hub.docker.com'
                    DOCKER_HUB_FULL_URL = 'https://' + DOCKER_HUB_URL
                    DOCKER_HUB_CREDENTIAL_ID = 'docker-hub'
                    DOCKER_IMAGE_NAME =  'ggnagpae1' + '/' + 'jenkins'
                }
            }
        }

        stage("Compile"){
            steps{
            sh "./gradlew compileJava"
            }
        }

        stage("Unit Test"){
            steps{
                sh "./gradlew test"
            }
        }

       stage("Code Coverage"){
            steps{
                sh "./gradlew test jacocoTestReport"
                publishHTML(target: [
                            reportDir: 'build/reports/jacoco/test/html',
                            reportFiles: 'index.html',
                            reportName: "JaCoCo Report"])
                sh "./gradlew test jacocoTestCoverageVerification"
            }
        }
        stage("Static Code Analysis"){
            steps{
                sh "./gradlew checkstyleMain"
                        publishHTML(target: [
                                    reportDir: 'build/reports/checkstyle/',
                                    reportFiles: 'main.html',
                                    reportName: "Checkstyle Report"])
                        sh "./gradlew test jacocoTestCoverageVerification"
           }
       }

       stage("gradle build"){
            steps{
                sh "./gradlew clean build"
            }
       }

       stage('Build') {
           steps {
               sh 'docker build -t ggnagpae1/jenkins .'
           }
       }

       stage('Login') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
       }

       stage('Push') {
            steps {
                sh 'docker push ggnagpae1/jenkins:latest'
            }
       }

        stage("Deploy to staging"){
            steps{
                sh 'docker run -d --rm -p 8765:8080 --name jenkins ggnagpae1/jenkins'
            }
       }




    }
}