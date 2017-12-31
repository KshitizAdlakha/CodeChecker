
#### Repository moved from https://github.ccs.neu.edu/cs5500/team-1/tree/master/Phase_C

### CodeChecker is a plagiarism detection tool for programming assignments written in JAVA language.

---

### Instructions to run the application locally

1. Install Maven on your local system
2. Run `mvn install` to run import the dependencies included in the pom.xml file
3. Run `mvn tomcat7:run` to run the application
4. Browse to http://localhost:8080/code-checker/#/ to access the application


#### Alternate Way using IntelliJ IDEA:

1. Open the project through project window
2. Open the Maven Tool Window and click on install under the code-checker -> Lifecycle tab
3. Click on tomcat7:run under the code-checker -> Plugins -> tomcat7 tab
4. Browse to http://localhost:8080/code-checker/#/ to access the application

### Running the CodeChecker

1. Create an account by filling the form on the http://localhost:8080/code-checker/#/signup page
2. Add the name of the assignment, select the two files containing the assignment submissions
3. Click on Analyze Results
4. You will be navigated to the result page, containing a side-by-side of the files
