# Confserver
Spring Cloud Config Server project to provide a centralized location for all configuration.

See: 
https://cloud.spring.io/spring-cloud-config/

## Service Registry configuration
For simple usage of the Service Registry you only need to add
`@EnableEurekaClient` to the main application class and configure 
the `application.yml` to point to the Eureka Server.

