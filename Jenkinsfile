pipeline{
    agent any
    stages{
        stage("Compile"){
            steps{
            sh "./gradlew compileJava"
            }
        }
        stage("UnitTest"){
            steps{
                sh "./gradlew test"
            }
        }
        stage("Code Coverage"){
            steps{
                sh "./gradlew test jacocoTestReport"
                sh "./gradlew test jacocoTestCoverageVerification"
            }
        }
    }
}