node {
   stage('Clone the project') { // for display purposes
      // Get code from a GitHub repository
      git 'https://github.com/Lora-Uroshlieva/java_demo2.git'
   }
   stage("Install dependencies") {
       // Install all libs and compile
      if (isUnix()) {
         sh "mvn clean install -DskipTests"
      } else {
         bat(/mvn clean install -DskipTests/)
      }
   }
   stage('Checkstyle') {
      // Run ckeckstyle plugin
      if (isUnix()) {
         sh "mvn checkstyle:checkstyle"
      } else {
         bat(/mvn checkstyle:checkstyle/)
      }
   }
   try {
    stage("Execute tests") {
           // Execute selenium tests
          if (isUnix()) {
             sh "mvn clean test -Ddatabase.password=1234"
          } else {
             bat(/mvn clean test -Ddatabase.password=1234/)
          }
       }
   } catch (err) {
           currentBuild.result = 'FAILED'
   }
   stage ('Allure Report') {
    allure([
            includeProperties: false,
            jdk: '',
            properties: [],
            reportBuildPolicy: 'ALWAYS',
            results: [[path: 'target/allure-results']]
            ])
   }
}