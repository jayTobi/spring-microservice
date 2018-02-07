# Confserver
Spring Cloud Config Server project to provide a centralized location for all configuration.

See: 
https://cloud.spring.io/spring-cloud-config/

## Service Registry configuration
For simple usage of the Service Registry you only need to add
`@EnableEurekaClient` to the main application class and configure 
the `application.yml` to point to the Eureka Server.

## Spring Cloud Bus + Push Notifications
You can enable push notification on changes, e.g. using webhooks in Github.
Everything needed for this is already integrated in this project.

See: http://cloud.spring.io/spring-cloud-static/spring-cloud-config/2.0.0.M5/single/spring-cloud-config.html#_push_notifications_and_spring_cloud_bus
 
 
For testing purpose you can use a local git and commit the changes. 
This should be detected and broadcasted. 