# Accounting Service
A example micro service demonstrating simple and more advanced Spring Security
concepts to protect (web / rest) services.

## Security basics and definitions
Security - or better securing an application - is a wide area and filled with plenty of different concepts, a few advanced topics 
like OAuth2 will be covered here, but some are out of scope. 
To begin with we define the terms used, not only in Spring Security, and then we provide some examples:

### Definitions
#### Authentication
According to Wikipedia authentication _is the act of confirming the truth of an attribute of a single piece of data claimed true by an entity. In contrast with identification, which refers to the act of stating or otherwise indicating a claim purportedly attesting to a person or thing's identity, authentication is the process of actually confirming that identity_

For securing (web) apps it mostly breaks down to 2 common types
1. **single-factor authentication**: where the user normally provides her user name and password to proof his identity.
2. **two-factor authentication**: an additional level of security by using additional information, 
e.g. using Google Authenticator where the user first provides her username/password and then needs to input an additional digit from the Authenticator app

#### Authorization    
Authorization is the function of specifying access rights/privileges to resources related to information 
security and computer security in general and to access control in particular. More formally, "to authorize" is to define an access policy

Authorization can be done in a couple of ways 
1. **Role based access control (RBAC) / Role based security**: Often used and simple, but often a good choice for small apps. 
The security decisions is based on one or more roles associated to a user (principal), e.g. ADMIN role that allows
2. **Permission based**: Allows fine-grained access control on methods/actions and provides a good way to manage permissions, roles and complex security scenarios. 
3. **Access Control list (ACL)**: fine-grained access control on domain objects (not only methods / actions), e.g. database entries, i.e. entity instances of a row

#### CORS (Cross-Origin Resource Sharing)


### Spring Security Concepts
Here a definition of the most important core concepts of Spring Security:

1. SecurityContextHolder, to provide access to the SecurityContext.
2. SecurityContext, to hold the Authentication and possibly request-specific security information.
3. Authentication, to represent the principal in a Spring Security-specific manner.
4. GrantedAuthority, to reflect the application-wide permissions granted to a principal, e.g. ADMIN role.
5. UserDetails, to provide the necessary information to build an Authentication object from your application’s DAOs or other source of security data.
6. UserDetailsService, to create a UserDetails when passed in a String-based username (or certificate ID or the like). 
7. AccessDecisionManager, main interface responsible for making access-control decisions
8. Authentication, object representing the principal requesting access, 
a "secure object" and a list of security metadata attributes which apply for the object (such as a list of roles which are required for access to be granted).  



## Overview of Spring Security (modules)
The Spring Security project consists of several modules with a 
specific use case that can be included if needed.

### Core: spring-security-core.jar
Contains all required classes and interfaces and is needed
by every project using Spring Security.

### Web: spring-security-web.jar
Filters and web-security infrastructure code.
Needed for securing anything with Servlet API, e.g.
URL-based access-control.

### Config: spring-security-config.jar
Module for configuration of Spring Security (XML or Java config).

### OAuth 2.0 Core, Client, JOSE
Support for industry-standard protocol for authorization.

Details will be provided in this example project and can also 
be found here: https://oauth.net/2/

### LDAP: spring-security-ldap.jar
LDAP authentication and provisioning code.

### ACL: spring-security-acl.jar
Specialized domain object access control list implementation, 
allowing fine-grained security on domain objects, e.g. entity instances.

### Test - spring-security-test.jar
Support for testing with Spring security.

For more details and more modules 
see: https://docs.spring.io/spring-security/site/docs/5.0.1.RELEASE/reference/htmlsingle/#modules

## Keycloak definitions 
### Groups vs. Roles
In the IT world the concepts of Group and Role are often blurred and interchangeable. 
In Keycloak, Groups are just a collection of users that you can apply roles and attributes to in 
one place. Roles define a type of user and applications assign permission and access control to roles.

Aren’t Composite Roles also similar to Groups? Logically they provide 
the same exact functionality, but the difference is conceptual. Composite roles should be used to 
apply the permission model to your set of services and applications. Groups should focus on collections of users and their roles in your organization. 

**Use groups to manage users. Use composite roles to manage applications and services.**

# Getting started
## Keycloak Docker image 
To start a Keycloak instance in Docker for testing purpose you can run the following command
and access the Keycloak Admin Console in your browser http://localhost:8900/
  
`docker run -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin  -p 8900:8080 jboss/keycloak`

## Keycloak Configuration
Getting the Keycloak configuration right can be quite challenging because of the nearly endless possibilities.
A good starting point for REST services is to use resources, scopes, permission, policies and roles as follows:

1. Go to your web browser and open the admin console and login with admin/admin
2. Create a dedicated realm named "spring-microservice"
3. Create a new client, e.g. "accounting" for the accounting microservice 
4. Select the accounting client and enable authorization on client settings page
5. Click on the "Roles" tab (on the right tab-menu, not the left menu) and create a role, e.g. "READ_ONLY" for this client
6. After saving a new Authorization tab appears - click on it
7. Go to "Authorization Scopes" tab and add your needed HTTP verbs as scopes, e.g. GET, PUT, POST, DELETE
8. Go to "Resources" tab and add a resource for all your REST (resource) endpoints, e.g. /accounting in the example
9. Click on the newly created resource and add all required (authorization) scopes
10. Go to "Policy" tab and create a "Role Policy" - select a (client) role, e.g. the created "READ_ONLY"
11. Go to "Permissions" tab and create a "Scope based" permission: provide a name e.g. "accounting_read_permission", select the "accounting" resource
add a scope ("GET") and apply policy "READ_ONLY" and save the settings
12. Click on "Users" (left menu) and select a user or create a new
13. After clicking on a user, select "Role Mappings" and then e.g. 
"Client Roles" for "accounting" and assign "Read_Only" role 
14. To check the permission go to "Clients: accounting" -> Authorization -> Evaluate:
Select a client, a user and a resource - don't forget to click add - and then click on evaluate.
Now you see which scopes on the resource are permitted or denied and which policy was responsible for the decision.
 
## Integrate
 
#### Links
1. https://projects.spring.io/spring-security/
2. https://docs.spring.io/spring-security/site/docs/5.0.1.RELEASE/reference/htmlsingle/
3. https://docs.spring.io/spring-security/site/docs/5.0.1.RELEASE/reference/htmlsingle/#modules
4. https://oauth.net/2/