#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    stage('check java') {
        sh "java -version"
    }

    stage('clean') {
        sh "./mvnw clean"
    }

    stage('backend tests') {
        try {
            sh "./mvnw test"
        } catch (err) {
            throw err
        } finally {
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }

    stage('packaging') {
        sh "./mvnw package -Pprod -DskipTests"
        archiveArtifacts artifacts: '**/target/*.war', fingerprint: true
    }

    /*stage('quality analysis') {
        withSonarQubeEnv('Sonar Server') {
            sh "./mvnw sonar:sonar"
        }
    }*/

}
