pipeline {
    agent none
    stages {
        stage('Build') {
            agent { label 'master' }
            environment {
                GROOVY_HOME = tool name: 'groovy-2.5', type: 'hudson.plugins.groovy.GroovyInstallation'
            }
            steps {
                echo "Building ..."
                sh '${GROOVY_HOME}/groovy -version'
            }
        }
        stage('Quality Control') {
            agent { label 'master' }
            steps {
                echo "Quality Control ..."
            }
        }
        stage('Deploy (DEV)') {
            agent { label 'master' }
            steps {
                echo "Deploy (DEV) ..."
            }
        }
        stage('Test (DEV)') {
            agent { label 'master' }
            steps {
                echo "Test (DEV) ..."
            }
        }
        stage('Deploy (TEST)') {
            agent { label 'master' }
            steps {
                echo "Deploy (TEST)"
            }
        }
        stage('Test (TEST)') {
            agent { label 'master' }
            steps {
                echo "Test (TEST) ..."
            }
        }
        stage('Deploy (PROD)') {
            agent { label 'master' }
            steps {
                echo "Deploy (PROD)"
            }
        }
        stage('Verify (PROD)') {
            agent { label 'master' }
            steps {
                echo "Verify (PROD) ..."
            }
        }
        stage('Acceptance') {
            agent { label 'master' }
            steps {
                echo "Acceptance ..."
            }
        }
    }
}