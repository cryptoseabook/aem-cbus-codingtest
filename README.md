# [Cbus AEM Last minute Coding Test](https://www.cbussuper.com.au/)

# Coding Test Requirement
The task is to create a simple AEM application that would get list of member data (First Name, Last Name, City) from a database (Eg: MySQL) and show that in the front-end as a table in a page.

- Please explain the AEM folder structure
- Provide the sightly code for the front end component
- Java code - service for querying the database and sending back to the front-end
- Provide a small summary of steps of how you would be deploying the OSGi bundle with this java code.
- Please write the necessary Unit Tests for the Java code that you are writing.

# Disclaimer
This is a last minute coding test requirement, I dont have AEM installed in my local machine. Hence I cannot really do much testing in the aem site. 
In latest year 2019, I was working on spring boot + reactjs project, hadn't touch AEM for one more year.
However I will try my best to write the code in VSCode in short period of time to express how I tackle the problem and provide the solution at my absolute best.

# Solution Walk through
 - If you want to access data from mysql, out of box, you can use AEM DataSourcePool osgi bundle service to setup a connection to mysql
 - Then you can use Java JDBC API to perform database operations, such as executing a query
 - You could use maven achetype to generate the boilerplate code.
 - In the java core module directory, we will write our member models, jdbc api implementation and using sling simple servlet for the frontend to retrieve the infomation (All these will be refelected in the code, build and deployed as osgi bundle)
 - In the service part, the ticky bit is need to serialize the object by using xml 
 - Then you need to deploy the bundle to AEM containing mysql-connector-jar file, this might be tricky
 - OSGI part is always tricky sometimes, it might require modify pom files.
 - front end part should reside under ui.apps/ modules apps/aem-cbus-codingtest/components/content/membertable


## Project folder structure explaination 

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs as well as Hobbes-tests
* ui.content: contains sample content using the components from the ui.apps
* ui.tests: Java bundle containing JUnit tests that are executed server-side. This bundle is not to be deployed onto production.
* ui.launcher: contains glue code that deploys the ui.tests bundle (and dependent bundles) to the server and triggers the remote JUnit execution

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage
    
Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish
    
Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle

## Testing

There are three levels of testing contained in the project:

* unit test in core: this show-cases classic unit testing of the code contained in the bundle. To test, execute:

    mvn clean test

* server-side integration tests: this allows to run unit-like tests in the AEM-environment, ie on the AEM server. To test, execute:

    mvn clean integration-test -PintegrationTests

* client-side Hobbes.js tests: JavaScript-based browser-side tests that verify browser-side behavior. To test:

    in the browser, open the page in 'Developer mode', open the left panel and switch to the 'Tests' tab and find the generated 'MyName Tests' and run them.


## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html

## Contact
@seabookchen