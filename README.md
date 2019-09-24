# Octoq

Octoq is a question and answer site for Mitrais Staff. It features questions and answers on a wide range of topics in computer programming, employee, event, culture and other topic that related to Mitrais.

## Contents
* [Requirement](#Requirement)
* [Design](#Design)
* [Implementation and Testing](#Implementation and Testing)
* [Deployment](#Deployment)
* [Built With](#Built With)
* [Authors](#Authors)
* [License](#License)

## Requirement
#### Functionality
- Authentication 
    - Provide functionality for user authentication based on email and password. In this feature will generate access token, the token will allow user to access menu based on user restriction. 
- Manage User
    - Provide functionality to manage user such as register user, get user data, update, and delete user.
- Manage Question
    - Provide functionality to manage question such as ask a question, get question, update, and delete question.
- Manage Answer
    - Provide functionality to manage answer of question such as give answer, get an answer, update, and delete answer.
- Manage Comment
    - Provide functionality to manage comment of question or answer such as give a comment, get comment, update, and delete comment.
- Manage Rate
    - Provide functionality to manage rate of question or answer such as give a rate, get rate, update, and delete rate.
#### Non Functionality
- Microservice Architecture
    - This project is developed under Microservice architecture using Spring cloud
- Gateway Access
    - This project using spring cloud gateway to route API.
- Multiple Database
    - This project using two database, MongoDb and PostgreSql

### Future
#### Functionality
- Question 
    - In the future user should add a category for the question.
#### Non Functionality
- O-Auth 2

## Project Structure
 - Eureka Service
 - Gateway Service
 - Octoq Website
 - Question Service
 - User Service

## Design
Octoq adopts Client Service (REST) architecture. API architecture is like below: 
```
API Application 
└────── Controller 
	├── Service 
	├── Model 
	└── Repository
```

UI Architecture is like below: 
```
UI Application 
├── Components
├── Services 
├── Store 
├── View
└── App.vue
```

## Implementation and Testing
Octoq is implemented using VueJs as the front end and Spring Boot as the backend. Backend is completed with unit test using JUnit. 

## Deployment

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
These tools is required to install to run the application 
- [Git](https://git-scm.com/book/en/v2/Getting-Started-Installing-Git)
- [Java 11](http://jdk.java.net/java-se-ri/11)
- [Nodejs](https://nodejs.org/en/download/)
- [Intellij IDEA](https://www.jetbrains.com/help/idea/installation-guide.html) / [Eclipse](https://www.eclipse.org/downloads/packages/installer) / [Visual Studio Code](https://code.visualstudio.com/download) or any other IDE to open Java code and Vue js
- [MongoDb](https://www.mongodb.com/)
- [PostgreSql](https://www.postgresql.org/)

#### Open backend project
- Clone the source code into local directory
    ```
    Git clone https://github.com/Mitrais/octoq.git
    ```
- Open project using Intellij / Eclipse
    - Run Eureka-service
    - Run gateway-service
    - Run user-service
    - Run question-service


#### Open Website project
- Open Octoq-website using Visual Studio Code or other tools.
- Download project dependency.
  ```
  npm install
  ```
- Run web application
  ```
  npm run serve
  ```
- Open address below to access the initial website
  ```
  http://localhost:8080
  ```


## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

## Authors
* Rai Suardhyana Arijasa - *Initial work*

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
