# Eureka! Clinical User Agreement Webapp
[Atlanta Clinical and Translational Science Institute (ACTSI)](http://www.actsi.org), [Emory University](http://www.emory.edu), Atlanta, GA

## What does it do?
It provides web pages for users to sign user agreements and for administrators to create and update a user agreement. It also implements a proxy servlet and router for web clients to access the web services provided by eurekaclinical-user-agreement-service.

## Version 2.0 development series
Latest release: [![Latest release](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/eurekaclinical-user-agreement-webapp/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eurekaclinical/eurekaclinical-user-agreement-webapp)

## Version history
No final releases yet

## Build requirements
* [Oracle Java JDK 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)
* [Maven 3.2.5 or greater](https://maven.apache.org)

## Runtime requirements
* [Oracle Java JRE 8](http://www.oracle.com/technetwork/java/javase/overview/index.html)
* [Tomcat 7](https://tomcat.apache.org)
* Also running
  * The [eurekaclinical-user-service](https://github.com/eurekaclinical/eurekaclinical-user-agreement-service) war
  * The [cas-server](https://github.com/eurekaclinical/cas) war

## Proxied REST APIs
You can call all of [eurekaclinical-user-service](https://github.com/eurekaclinical/eurekaclinical-user-agreement-service)'s REST APIs through the proxy. Replace `/protected/api` with `/proxy-resource`. The point of doing this is for web clients -- you can deploy the webapp on the same server as web client, and deploy the service on a separate server.

## Building it
The project uses the maven build tool. Typically, you build it by invoking `mvn clean install` at the command line. For simple file changes, not additions or deletions, you can usually use `mvn install`. See https://github.com/eurekaclinical/dev-wiki/wiki/Building-Eureka!-Clinical-projects for more details.

## Performing system tests
You can run this project in an embedded tomcat by executing `mvn tomcat7:run -Ptomcat` after you have built it. It will be accessible in your web browser at https://localhost:8443/eurekaclinical-user-agreement-webapp/. Your username will be `superuser`.

## Installation
### Configuration
This webapp is configured using a properties file located at `/etc/ec-user/application.properties`. It supports the following properties:
* `eurekaclinical.userwebapp.callbackserver`: https://hostname:port
* `eurekaclinical.userwebapp.url`: https://hostname:port/eurekaclinical-user-webapp
* `eurekaclinical.userservice.url`: https://hostname.of.userservice:port/eurekaclinical-user-service
* `cas.url`: https://hostname.of.casserver:port/cas-server
* `eurekaclinical.userwebapp.localregistrationenabled`: true or false to enable/disable registering for an account managed by this project; default is true.
* `eurekaclinical.userwebapp.githuboauthkey`: the key for registering using a GitHub OAuth account.
* `eurekaclinical.userwebapp.githuboauthsecret`:  the secret for registering using a GitHub OAuth account.
* `eurekaclinical.userwebapp.globusoauthkey`:  the key for registering using a GitHub OAuth account.
* `eurekaclinical.userwebapp.globusoauthsecret`:  the secret for registering using a Globus OAuth account.
* `eurekaclinical.userwebapp.googleoauthkey`:  the key for registering using a Google OAuth account.
* `eurekaclinical.userwebapp.googleoauthsecret`:  the secret for registering using a Google OAuth account.
* `eurekaclinical.userwebapp.twitteroauthkey`:   the key for registering using a Twitter OAuth account.
* `eurekaclinical.userwebapp.twitteroauthsecret`:  the secret for registering using a Google OAuth account.
* `eurekaclinical.userwebapp.demomode`: true or false depending on whether to act like a demonstration; default is false.
* `eurekaclinical.userwebapp.ephiprohibited`: true or false depending on whether to display that managing ePHI is prohibited; default is true.

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
* [Javadoc for latest development release](http://javadoc.io/doc/org.eurekaclinical/eurekaclinical-user-agreement-webapp) [![Javadocs](http://javadoc.io/badge/org.eurekaclinical/eurekaclinical-user-webapp.svg)](http://javadoc.io/doc/org.eurekaclinical/eurekaclinical-user-agreement-webapp)

## Getting help
Feel free to contact us at help@eurekaclinical.org.

