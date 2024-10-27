# Sample Company

## Technologies 

* Java 21
* Spring Boot 3
* OpenApi
* Docker
* Sonarqube
* Lombok
* Gson
* H2

## How to run the application
You can download the application from this URL
https://github.com/MahidharHub/sample-company
* Download the greenbone sample application docker file
````

docker pull greenbone/exercise-admin-notification

````

* Run docker image with this command
````

docker run -p 8080:8080 greenbone/exercise-admin-notification:latest

````
You can download the sample application microservice from this URL
https://github.com/MahidharHub/sample-company

### Running with maven
* Do a clean build
````
mvn clean install
````
* Run the jar
````
Java -jar target/samplecompany-0.0.1-SNAPSHOT.jar
````

### Running in Docker Container
* Build the docker image with Jib command
````
mvn compile jib:dockerBuild
````
* Docker image will be build
  
````
docker run -p 8085:8085 mahidhar/samplecompany:3
````
* In the application.properties file there is a property
* greenbone.service.uri=http://host.docker.internal:8080/api/notify
* using 'host.docker.internal' instead of 'localhost' helps in connecting with other docker container in this case greenbone Sample Apliication docker running on port 8080
* Open APi endpoints
````
http://localhost:8085/swagger-ui/index.html
````

* H2 Database endpoint
````
http://localhost:8085/console/
````
* Some records are being ingested which are read from computers.csv file
## Testing the endpoints
1. Get all the computers 
2. Get  ````  http://localhost:8085/computers/ ````
3. Create a computer
4. Post  ````  http://localhost:8085/computers/ ````
5. Get a computer with id
6. Get  ````  http://localhost:8085/computers/{id} ````
7. Delete a computer with id
8. Delete ````  http://localhost:8085/computers/{id} ````
9. Update a computer with id
10. Put ````  http://localhost:8085/computers/{id} ````
11. Get all available unassigned computers 
12. Get ````  http://localhost:8085/computers/available ````
13. Get all assigned computers to a specific employee
14. Get ````  http://localhost:8085/computers/assigned/{employee}  ````


## Areas for improvement
* Testing coverage can be improved. It is showing as 81%  lines in Intellij code coverage including Integration tests.
* Sonarqube code improvement suggestions can be implemented.
* Spring security can be implemented to restrict the access to general employees to access available computers
* Prometheus Grafana Loki can be configured to get the trace id across microservices.
* Kubernetes cluster and related helm files can be created.


