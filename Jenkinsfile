#!/usr/bin/env groovy
pipeline{
    agent any

    //Define stages for the build process
    stages{
        //Define the deploy stage
        stage('Deploy'){
            steps{
                script{
                    docker.withRegistry('https://gt-build.hdap.gatech.edu'){
                        //Build and push the database image
                        def fhirFilterImage = docker.build("pacerdecedentindex:1.0", "-f ./Dockerfile .")
                        fhirFilterImage.push('latest')
                    }
                }
                script{
                    docker.withRegistry('https://797827902844.dkr.ecr.us-east-2.amazonaws.com', 'ecr:us-east-2:open-mdi-credential'){
                        //Build and push the database image
                        def openMdiImage = docker.build("om-java-ms-gtri-decedent-index:1.0", "-f ./Dockerfile .")
                        openMdiImage.push('latest')
                    }
                }
            }
        }

        //Define stage to notify rancher
        stage('Notify'){
            steps{
                script{
                    rancher confirm: true, credentialId: 'gt-rancher-server', endpoint: 'https://gt-rancher.hdap.gatech.edu/v2-beta', environmentId: '1a7', environments: '', image: 'gt-build.hdap.gatech.edu/pacerdecedentindex:latest', ports: '', service: 'MortalityReporting/decedent-index', timeout: 60
                }
            }
        }
    }
}