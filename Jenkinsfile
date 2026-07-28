#!/usr/bin/env groovy

pipeline {

    agent any

    tools {
        jdk "jdk8u292-b10"
    }
    
    stages {
        
        stage('Build') {
        
            steps {

                echo 'Building project.'
                sh 'chmod +x gradlew'
                sh './gradlew clean build --stacktrace --warn'
            }
        }
    }
}
