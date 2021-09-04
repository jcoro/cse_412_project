# Getting Started

### Needed for the App to Run:

* [Maven](https://maven.apache.org/guides/index.html)
* [PostgreSQL Database](https://www.postgresql.org/)
* [Postgres JDBC Driver](https://jdbc.postgresql.org/)
* [Node Package Manager (npm)](https://www.npmjs.com/)
* [NodeJs](https://nodejs.org/en/)

Recommended:
* [IntelliJ](https://www.jetbrains.com/idea/)
* [PGAdmin4](https://www.pgadmin.org/)

## Step 1 - Create Database
Create a Database Schema (Easy in PGAdmin).
Spring will create and populate the tables for you.

![PGAdmin](https://github.com/jcoro/cse_412_project/blob/master/src/main/angularclient/src/assets/images/1.png?raw=true)

## Step 2 - Clone Repo
[Clone the Repo into IntelliJ](https://blog.jetbrains.com/idea/2020/10/clone-a-project-from-github/)

## Step 3 - Settings
Individual settings (i.e., settings that will vary between developers - passwords, urls, names, etc.)
will are put in `settings.xml`

![settings.xml](https://github.com/jcoro/cse_412_project/blob/master/src/main/angularclient/src/assets/images/2.png?raw=true)

IntelliJ lets you easily access this file:
1) Right click on pom.xml file
2) Hover over Maven and choose create `settings.xml`

![Access settings.xml](https://github.com/jcoro/cse_412_project/blob/master/src/main/angularclient/src/assets/images/3.png?raw=true)

With the properties in the `settings.xml` file, Maven will access these properties.
Maven looks in `application.properties`,and expands the values from `settings.xml`.
This way, we can keep all sensitive data out of version control.

![Maven Global Settings](https://github.com/jcoro/cse_412_project/blob/master/src/main/angularclient/src/assets/images/4.png?raw=true)

## Step 4 - Launch Spring Boot
Before lanching Spring Boot, open maven tab on the right
1) Choose cse_412_project
2) Choose Lifecycle
3) Double click install or package to install all the maven packages in pom.xml files
![Install Maven Packges](https:github.com/jcoro/cse_412_project/blob/master/src/main/angularclient/src/assets/images/7.png?raw=true)

The entry point for the Spring Boot application is in:
`src/main/java/com/example/cse_412_project/Cse412ProjectApplication.java`

![Spring Boot Entry Point](https://github.com/jcoro/cse_412_project/blob/master/src/main/angularclient/src/assets/images/5.png?raw=true)

The Spring Boot application is basically a REST service which the Angular client accesses, so keep it running in IntelliJ.

## Step 5 - Launch the Angular Client
Open a terminal at the directory in the application called: `angularclient` 
1) Type `npm install` to install all the needed packages
2) Type: `ng serve` to launch the Angular app
![terminal](https://github.com/jcoro/cse_412_project/blob/master/src/main/angularclient/src/assets/images/6.png?raw=true)

