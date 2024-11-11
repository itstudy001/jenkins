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
             app = docker.build("ggnagpae1/jenkins") #Push Image 단계에서 빌드번호를 붙이기 때문에 옵션 제거
         }
         stage('Push image') {
             docker.withRegistry('https://registry.hub.docker.com', 'docker-hub') #업로드할 레지스트리 정보, Jenkins Credentials ID {
                 app.push("latest") #image에 latest를 태그로 붙인 후 Push
         }
}