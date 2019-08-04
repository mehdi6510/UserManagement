# Simple User Management

This is a sample CRUD project for user management which has been developed with these technologies.

```
* Java 8
* Spring Boot 2
* H2 in memory database
* Angular 8 
```

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development 
and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Capabilities

This application is doing four main operation for simple user management such as:

```
* Show all users list.
* show details information for one user
* Create new user
* Update user detail
* Delete user 
```

### Services and rest endpoints:

This app contains only one controller with five rest endpoints that ui call them and interact with server through them.

```
* Get all users - GET (http://localhost:8080/usermanagement/api/users)
* Get an user - GET (http://localhost:8080/usermanagement/api/users/{id})
* Create user - POST (http://localhost:8080/usermanagement/api/users)
* Update user - PUT (http://localhost:8080/usermanagement/api/users/{id})
* Delete user - DELETE (http://localhost:8080/usermanagement/api/users/{id})
```

### Prerequisites

* [Java SE 8](https://www.oracle.com/technetwork/java/javase/downloads/) - Java SE Development Kit 8 (JDK 1.8)
* [Apache Maven](https://maven.apache.org/) - Dependency Management (Maven 3.3+)
* [Node.js](https://nodejs.org/en/) - Node.js JavaScript runtime (for example 12.6.0)

You should do these steps:

```
* Install JDK and set JAVA_HOME on your system
* Install Node.js
* Copy and extract Apache Maven in a directory and set MAVEN_HOME on your system
```

### Installing

At first configure JDK and also Apache Maven in your idea.
Then you can get a clone of this project and run maven clean install in your idea 
or run maven command in the project directory: 

#### `mvn clean install`

This command prepare the project backend dependencies and run npm for frontend dependencies,
It run the following command for preparing all Angular library and modules 

#### `npm install`

In the IDEA you should set Run\Debug configuration to run the Backend. for this you should
specify Main class and classpath of this module and JRE and for running frontend you should
in it's directory path run (ng serve) command or click on the start script in package.json file.

##### For Run\Debug Configuration dev server:

```
* Main Class: com.afifi.usermng.BackendApplication.java
* Working directory: for example .\x\y\z\UserManagement
* Classpath of module: backend
* JRE: JAVA 1.8 SDK
```

##### For frontend (UI) dev server:
```
Run this script in package.json file:
  
  "scripts": {
    "start": "ng serve",
    ...
  }
  
or in (\frontend\src\main\web) directory run this command:

ng serve
```

##### For running on production mode:
After you run maven clean install, The jar file of project has be prepared and
you can in the `.\backend\target` directory run this command.

```
java -jar usermanagement.jar
```

The jar file contains embeded Apache Tomcat web server and serve the http requests on port 8080.

## Interacting with the application

If you run app on dev mode ,you can use the application with this url:

```
http://localhost:4200/
```

and if run it on production mode, enter the following url in browser:

```
http://localhost:8080/
```

## Running the tests

Testing can be done in two way, First running the class AllTests.java. It is a suite test and run all other
unit tests classes. Another way is running maven clean install either in your idea or in cmd or linux terminal.

### Break down into end to end tests

These tests verify all method in service and controller class. Integration test cases uses MockMVC and RestTemplate for testing the rest
endpoints in controller class and Mockito for testing methods in service class.If you run
mvn clean install, the results of tests show like below:

```
[INFO] Results:
[INFO] 
[INFO] Tests run: 34, Failures: 0, Errors: 0, Skipped: 0
```

## Deployment

For deploying this app, it is enough to run the jar file in a machine:

```
java -jar .\x\y\UserManagement\backend\target\usermanagement.jar
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency and Build Management

## Version

The version of this app:

#### `1.0`

## Release Date

The planned date to release this application is:

#### `2019-08-12`

## Authors

* **Mehdi Afifi** - [mehdi6510](https://github.com/mehdi6510)

## Acknowledgments

This is sample CRUD app and you can expand it to learn how to bootstrap an application with Spring Boot2 and Angular8.

##### Best Regards
