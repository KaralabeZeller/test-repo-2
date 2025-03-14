# Spring Boot service módosítás

Backend for RePlant service

Basic environment 

- Java 17
- Spring Boot 3.4.2
- Flyway
- Spring Security
- Spring Data JPA


## Dev info

- Install: mvn clean install -DskipTests=true
- Run: mvn spring-boot:run
- Browse: http://localhost:8080

## Test

Products endpoint can be tested CRUD with curl or postman, e.g.:
curl -X GET http://localhost:8080/api/products