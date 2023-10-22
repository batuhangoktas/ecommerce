# ECommerce

Invoice entry system for employees as purchasing specialists on an e-commerce platform.


# Technology

-   Java 17
-   Spring Boot
-   H2
-   Redis
-   Kafka
-   Maven
-   Docker
-   JPA
-   Lombok
-   Swagger
-   Jwt
-   AOP
-   JUnit
-   JavaDoc

## Docker

In this project, application, redis, zookeeper, kafka, kafka-ui are dockerized.
```
mvn clean package
```

**Dockerfile**

To create an image of the application use -

```
docker build -t ecommerce:latest .
```

**Docker-Compose**

To run the new image, redis, zookeeper, kafka, kafka-ui, use -

```
docker-compose up -d    
```
All done!
The backend service runs on  [http://localhost:8080](http://localhost:8080/).

## Swagger

Swagger can be used for api specification can be accessed at the following URL -

```
http://localhost:8080/swagger-ui/index.html#/
```

## Postman Collection

-   [ECommerce.postman_collection.json](https://github.com/batuhangoktas/ECommerce/blob/master/postman/ECommerce.postman_collection.json)
-   ![](https://github.com/batuhangoktas/ecommerce/blob/master/postman/collection.png)


## Usage
All services except the /register and /login service work with the bearer token. Therefore, first of all, the /register and /login service should be called with

Accountant /register service
   

    {
        "firstName": "1_AccountantFirstName",
        "lastName": "1_AccountantLastName",
        "email": "1_AccountantEmail",
        "password": "1_AccountantPassword"
    }

Accountant /login service

    {
    "username": "1_AccountantEmail",
    "password": "1_AccountantPassword"
    }

Bearer token information should be added in the header field of other services as follows.

    Headers:
    		Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzd...

Bearer token is valid for 15 days.

Detailed API specification for all services is available in the swagger interface.

## Kafka

In cases where the invoice is not accepted, other teams can be informed by Kafka. The relevant kafka topic name is **"UnApprovedBill"**

Detailed information about unapproved invoices is available in the kafka-ui
```
http://localhost:8090/ui/clusters/local/all-topics/UnApprovedBill
```

## Unit Test

There are multiple unit test cases written to cover the different components of the application.

These:
```
AccountantControllerTest.java
AuthenticationControllerTest.java
BillControllerTest.java
ProductControllerTest.java
```

## JavaDoc

![](https://github.com/batuhangoktas/ecommerce/blob/master/postman/javadoc.png)

## Credit
**Batuhan Göktaş**

 - **Linkedin:** [@batuhangoktas](https://www.linkedin.com/in/batuhan-g%C3%B6kta%C5%9F-29035aa8/)
