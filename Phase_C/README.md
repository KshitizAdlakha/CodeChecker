# Instructions to run the application locally

1. Install Maven on your local system
2. Run `npm install` in the `NewCodeCheck/src/main/web-app/app` directory
3. Run `mvn install` to run import the dependencies included in the pom.xml file
4. Run `mvn tomcat:run` or `mvn tomcat7:run` to run the application
5. Browse to http://localhost:8080/code-checker/app/index.html#/ to access the application


### Alternate Way using IntelliJ IDEA:

1. Open the project through project window
2. Run `npm install` in the `NewCodeCheck/src/main/web-app/app` directory
3. Open the Maven Tool Window and click on install under the code-checker -> Lifecycle tab
4. Click on tomcat7:run under the code-checker -> Plugins -> tomcat7 tab
5. Browse to http://localhost:8080/code-checker/app/index.html#/ to access the application
