# scheduler-rest

Minimal [Spring Boot] app to schedule a message to print.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Swagger
Once the spring boot application is up and running you can navigate to the below path to access the [swagger definition](http://localhost:8080/swagger-ui.html#/).

## Java Doc
You can checkout the java doc files that has been generated under the mentioned directory
```~/scheduler-rest/doc/index.html```


## Copyright

Released under the Apache License 2.0. See the [LICENSE] file.
