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
        sh "./gradlew test jacocoTestCoverageVerification"
    }
}
    }
}