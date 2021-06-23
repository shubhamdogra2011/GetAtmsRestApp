# How to Run
1. Import as maven project in STS/eclipse.
2. Run as SpringBootApplication . The REST api would be accessible at port 8080 on the localhost

# Testing

### Manual Test Cases:
Request : GET http://localhost:8080/atms/all  
Response : Json List of atms. 200 OK

Request : GET http://localhost:8080/atms/{city}  
Response : Json List of atms filtered by city. 200 OK

Request : GET http://localhost:8080/atms/{city}  
Condition: City is not present.  
Response : 404 Not Found

Request : GET http://localhost:8080/atms/{city}  
Condition: Invalid city name such as alphanumeric string eg 123$!abc  
Response : 406 Not Acceptable

Request : GET http://localhost:8080/atms/all  
Condition: External ING api is down.(Simulated by disconnecting from the internet)  
Response : 500 Internal Server Error

### Run as > JUnit test
The following Tests will run :
- Smoke Test
- Integration Test
- Controller Unit Test

PreRequisites:  
- Verify/update mock lists required for Controller Unit Test in application.properties
