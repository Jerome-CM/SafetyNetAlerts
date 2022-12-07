# SafetyNets Alerts

This repository contains an application for emergency services. It's an REST API will return informations on the populations in the Culver city

## :rocket: Getting Started

* Maven : 3.8.6
* Java : 18
* Spring Boot : 2.7.3
* Spring : 5.3.22
* MySQL : 8.0.29
* Postman or other Api Manager

## :hammer_and_wrench: Configuration

On your IDE, configure the projet with your parameters
In application.properties file, located in : src/main/resources

Verify or change this parameters : 

* server
* server.port
* spring.datasource.url
* spring.datasource.username
* spring.datasource.password
* When you start the application for a first time, switch spring.jpa.hibernate.ddl-auto to create ( switch at "upgrade" for the next time )

## :books: Documentation

:warning: Your data on Json format in the Postman Body is required !

[The notice is available here](Documentation.pdf)

### Endpoints

For each endpoint, you have a POST,PUT and DELETE keywords

* /person
* /firestation
* /medicalRecord

### Consultations

* /firestation?stationNumber=3
* /childAlert?address=1509 Culver St
* /phoneAlert?firestation=3
* /fire?address=1509 Culver St
* /flood/stations?stations=1,2
* /personInfo?firstName=John&lastName=Boyd
* /communityEmail?city=Culver

## Run application

```
mvn spring-boot:run
```
 
 One time your application ready, with Postman, go to insert the data.json file in the BDD with this POST command :
 
 ```
/addData?fileName=data.json 
```

>  Note  
>  Important : data.json file must be in src/main/resources

## Run Tests

You can run test with Junit5 with : 

```
mvn test
```

## Get reports

#### JaCoCo
```
mvn test
```
JaCoCo report is create in : target/site/jacoco/index.html

####  Surefire
Get CSS with html report
```
mvn site -DgenerateReports=false
```
and the report
```
mvn surefire-report:report
```
Surefire report is create in : target/site/surefire-report.html

>  Note  
>  This project is an exercise of OpenClassRooms Java training
