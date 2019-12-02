# Repo Overview  

## PART A  
- API Tests by mocking Feign clients using Wiremock

#### Tech Stack  
* Spring Boot
* Spring Cloud
    * Feign
    * Hystrix (Circuit Breaker) 
    * Ribbon
* Wiremock
* Gradle  

#### How to Run  
`gradle build`  # runs as a Spring-Boot Test  

## PART B  
- API Tests with Wiremock and Karate  

#### Tech Stack  
* Spring Boot
* Spring Cloud
    * Feign
    * Hystrix (Circuit Breaker) 
    * Ribbon
* Wiremock
* Gradle
* **Karate**  

#### How to Run  

#####1. Bring up the server with test app properties  
`SPRING_CONFIG_LOCATION=./src/test/resources/application.yml  gradle clean bootRun`

#####2. Run Karate Tests  
`gradle karate`  

#### Configure to run tests on IntelliJ

Edit "Run Configuration" of the Karate Test file, and enter ":karate" for the Tasks field.

###TODOs
1. Fix Karate Tests
2. Add Karate gradle task to run app before running karate
3. Add a flag to make Karate become integration tests by stopping service mocks
