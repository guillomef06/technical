># Project introduction
>
> This Java project is a REST API that exposes the following services
> - POST http://localhost:8080/api/v1/customers
> - GET http://localhost:8080/api/v1/customers/{id}
> - GET http://localhost:8080/api/v1/customers

>## Project dependencies
>
> This project runs with:
> - Spring Boot 3.1.1
>   - spring-boot-starter-cache
>   - spring-boot-starter-data-jpa
>   - spring-boot-starter-web
>   - spring-boot-starter-validation
>   - spring-boot-starter-test
> - h2 database
> - lombok 1.18.28
> - modelmapper 3.1.1

>## Run the project
> 
> Once you have downloaded the project
> 
> The simplest way: open it in your favorite IDE, and run TestApplication class
> 
> The maven way: use the package phase to produce the jar file then type java -jar target/technical-0.0.1-SNAPSHOT.jar

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.1.1/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#web)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring cache abstraction](https://docs.spring.io/spring-boot/docs/3.1.1/reference/htmlsingle/#io.caching)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Caching Data with Spring](https://spring.io/guides/gs/caching/)

