pipeline{
    agent any


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

       stage('Build & Push Docker Image') {
           steps {
               echo 'Build & Push Docker Image'
               withCredentials([usernamePassword(
                       credentialsId: DOCKER_HUB_CREDENTIAL_ID,
                       usernameVariable: 'ggnagpae1',
                       passwordVariable: 'Dokki1025!')]) {

                   script {
                       docker.withRegistry(DOCKER_HUB_FULL_URL,
                                           DOCKER_HUB_CREDENTIAL_ID) {
                       app = docker.build('ggnagpae1' + '/' + 'jenkins')
                       app.push(env.BUILD_ID)
                       app.push('latest')
                       }
                   }
               }
           }
       }
    }
}