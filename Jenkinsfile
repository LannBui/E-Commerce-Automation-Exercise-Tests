pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/LannBui/E-Commerce-Automation-Exercise-Tests.git',
                    branch: 'master',
                    credentialsId: 'mlan-github-creds'
            }
        }

        stage('Build & Test') {
            steps {
                bat """
                    mvn test -DsuiteXmlFile=testng-suites/testng-smoke.xml
                """
            }
        }

        stage('Archive Reports') {
            steps {
                junit 'target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/surefire-reports/*.html', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            echo '🧹 Cleaning up...'
        }
    }
}
