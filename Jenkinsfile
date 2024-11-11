pipeline{
    agent any

    stages{
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

    }

    stage('Build image') {
             app = docker.build("ggnagpae1/jenkins")
         }
         stage('Push image') {
             docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') {
                 app.push("latest")
         }
}