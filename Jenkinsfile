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
    }
}