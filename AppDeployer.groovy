properties([[$class: 'JiraProjectProperty'], 
parameters([string(defaultValue: 'v1', description: 'Please provide a version number', 
name: 'APP_VERSION', trim: false)])])
node {
    stage("pull repo"){
        git 'https://github.com/dhujaev5/nodejs.git'

    }
    stage("Build Image"){
        sh "docker build -t app1:${APP_VERSION} ."
    }
    stage("Image Tag"){
        sh '''docker tag app1:${APP_VERSION} 714752049113.dkr.ecr.us-east-2.amazonaws.com/app1:${APP_VERSION}'''
    }
    stage("Login to ECR"){
        sh '''$(aws ecr get-login --no-include-email --region us-east-2)'''
    }
    stage("Push Image"){
        sh "docker images"
        sh "docker push 714752049113.dkr.ecr.us-east-2.amazonaws.com/app1:${APP_VERSION}"
    }
    stage("Notify"){
        sh "echo Hello"
    }
    stage("Run Container"){
        withDockerRegistry(url: )
    }
}
