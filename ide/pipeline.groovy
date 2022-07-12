pipeline {
    agent any


    stages {
        stage('Chcekout') {
            steps {
                // Get some code from a GitHub repository
                    git url: 'https://github.com/srinivas-manyam/jgsu-spring-petclinic.git', branch: 'main'
                }
            }
            stage('Build') {
            steps {

               sh './mvnw clean package'
            }

            post {
           //     If Maven was able to run the tests, even if some of the test
           //     failed, record the test results and archive the jar file.
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
    }
}
