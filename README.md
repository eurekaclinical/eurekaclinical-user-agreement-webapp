# Eureka! Clinical User Agreement Webapp
[Atlanta Clinical and Translational Science Institute (ACTSI)](http://www.actsi.org), [Emory University](http://www.emory.edu), Atlanta, GA

## What does it do?
It provides web pages for users to sign user agreements and for administrators to create and update a user agreement. It also implements a proxy servlet and router for web clients to access the web services provided by eurekaclinical-user-agreement-service.

## Version 3.0 development series
Latest release: [![Latest release](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/eurekaclinical-user-agreement-webapp/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/eurekaclinical-user-agreement-webapp)

## Version history
### Version 2.0
Updates dependencies and improves performance.

### Version 1.1
The version 1 series implements the proxy for eurekaclinical-user-agreement-service. It also provides basic screens for signing agreements, creating agreements and updating agreements.

## Build requirements
* [Oracle Java JDK 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)
* [Maven 3.2.5 or greater](https://maven.apache.org)

## Runtime requirements
* [Oracle Java JRE 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)
* [Tomcat 7](https://tomcat.apache.org)
* Also running
  * The [eurekaclinical-user-agreement-service](https://github.com/eurekaclinical/eurekaclinical-user-agreement-service) war
  * The [cas-server](https://github.com/eurekaclinical/cas) war

## Proxied REST APIs
You can call all of [eurekaclinical-user-agreement-service](https://github.com/eurekaclinical/eurekaclinical-user-agreement-service)'s REST APIs through the proxy. Replace `/protected/api` with `/proxy-resource`. The point of doing this is for web clients -- you can deploy the webapp on the same server as web client, and deploy the service on a separate server.

## Building it
The project uses the maven build tool. Typically, you build it by invoking `mvn clean install` at the command line. For simple file changes, not additions or deletions, you can usually use `mvn install`. See https://github.com/eurekaclinical/dev-wiki/wiki/Building-Eureka!-Clinical-projects for more details.

## Performing system tests
You can run this project in an embedded tomcat by executing `mvn process-resources cargo:run -Ptomcat` after you have built it. It will be accessible in your web browser at https://localhost:8443/eurekaclinical-user-agreement-webapp/. Your username will be `superuser`.

## Installation
### Configuration
This webapp is configured using a properties file located at `/etc/ec-user-agreement/application.properties`. It supports the following properties:
* `eurekaclinical.useragreementwebapp.callbackserver` = https://hostname:port
* `eurekaclinical.useragreementservice.url` = https://hostname.of.service:port/eurekaclinical-user-agreement-service
* `eurekaclinical.useragreementwebapp.url` = https://hostname:port/eurekaclinical-user-agreement-webapp
* `cas.url`=https://hostname.of.casserver:port/cas-server

A Tomcat restart is required to detect any changes to the configuration file.

### WAR installation
1) Stop Tomcat.
2) Remove any old copies of the unpacked war from Tomcat's webapps directory.
3) Copy the warfile into the Tomcat webapps directory, renaming it to remove the version. For example, rename `eurekaclinical-user-agreement-webapp-1.0.war` to `eurekaclinical-user-agreement-webapp.war`.
4) Start Tomcat.

## Maven dependency
```
<dependency>
    <groupId>org.eurekaclinical</groupId>
    <artifactId>eurekaclinical-user-agreement-webapp</artifactId>
    <version>version</version>
</dependency>
```

## Developer documentation
* [Javadoc for latest development release](http://javadoc.io/doc/org.eurekaclinical/eurekaclinical-user-agreement-webapp) [![Javadocs](http://javadoc.io/badge/org.eurekaclinical/eurekaclinical-user-agreement-webapp.svg)](http://javadoc.io/doc/org.eurekaclinical/eurekaclinical-user-agreement-webapp)

## Getting help
Feel free to contact us at help@eurekaclinical.org.

