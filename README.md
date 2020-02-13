# Repo Overview  

For a Spring-boot RestFul app using Feign client, explore - 
1. Writing API Tests using Wiremock for stubbing "Producer" services
2. Writing API Tests using   
    * Wiremock for stubbing "Producer" services  
    * [Karate](https://github.com/intuit/karate) - a BDD style DSL for automation testing

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
1. Add a flag to make Karate become integration tests by stopping service mocks
2. Run both spring-application and karate tests in one thread
3. Replace Wiremock with Karate Netty
