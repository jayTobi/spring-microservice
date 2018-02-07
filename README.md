# Spring Microservice
Example project using, Java, Kotlin, Spring, Spring Cloud to show possibilities on designing microservice architectures.

## Setup
### Startup order
Please start the modules in the following order:
1. RabbitMQ server via Docker
2. Eureka Server
3. Config Server
4. Greeting Service

## Submodule description
Brief overview of the submodules in this project. 
For details have a look at the readme files in each module.

### RabbitMq
Docker usage guide for RabbitMQ:  
https://hub.docker.com/_/rabbitmq/
 
Start the daemon (version 3.7 will automatically be downloaded) with

`docker run -d --hostname spring-ms-rabbit --name spring-ms-rabbit -p 5672:5672 -p 9000:15672 rabbitmq:3.7-management`

Check if it is running with `docker logs spring-ms-rabbit`

You can also access the web ui of RabbitMQ using port 9000.
Login is guest / guest.

### confserver 
Spring Cloud Config Server project to provide a centralized location for all configuration. 

### eurekaserver
Eureka server used as service registry. 

See: https://github.com/Netflix/eureka

### greetingservice
Simple microservice using spring cloud config and config server discovery with eureka.
