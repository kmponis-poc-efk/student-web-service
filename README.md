# student-web-service
A Spring Boot REST app that connects on MongoDB to CRUD a student.

## Prerequisites
* To run the application you need to have java8, git, mvn and mongodb installed. To initialise the database you have to go to web application tab 'admin'.

## Run
* Open new terminal and move to your workspace.
* Download project using your username: 
    ```sh
    $ git clone git clone https://[username]:pS7yeowUjsFCquZhj8ua@innersource.soprasteria.com/kostas.bonis/student-web-service.git
    ```
* Go to project: 
    ```sh
    $ cd student-web-service
    ```
* For Windows go to /src/main/resources and use the commented out logging file.
* Build project: 
    ```sh
    $ mvn clean install -DskipTests
    ```
* Start application: 
    ```sh
    $ java -jar target/student-web-service-0.0.1-SNAPSHOT.jar
    ```

## Use
* Go to browser: 
    ```sh
    localhost:9092/student/findAll?sessionId=testSession
    ```
    >If empty, initialise database from web UI (AngularJS application)
