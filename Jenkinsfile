pipeline {
    agent any

    environment {
        MAVEN_HOME = 'C:\\Program Files\\Apache\\maven-3.8.8'  // Adjust if necessary
        PATH = "${env.MAVEN_HOME}\\bin;${env.PATH}"
    }

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
                echo '🚀 Running Specified TestNG Suite...'
                catchError(buildResult: 'UNSTABLE', stageResult: 'FAILURE') {
                    bat 'mvn clean test -DsuiteXmlFile=testng-suites/testng.xml'
                }
            }
        }

        stage('Archive Reports') {
            steps {
                echo '📦 Archiving test results...'
                junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                archiveArtifacts artifacts: 'target/surefire-reports/*.html', allowEmptyArchive: true

            }
        }
        stage('End') {
            steps {
                echo '🏁 Pipeline complete.'
            }
    }

    post {
        always {
            echo '🧹 Cleaning up...'
        }
        success {
            echo '✅ Build succeeded with all tests passing.'
        }
        unstable {
            echo '⚠️ Build marked UNSTABLE due to test failures (e.g., assertions).'
        }
        failure {
            echo '❌ Build failed due to critical errors (e.g., compile or Maven failure).'
        }
    }
}
