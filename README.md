## Overview
This RESTful API provides a web messaging service (like Twitter) built on the Java EE platform. The Jersey library is used to implement the JAX-RS API for RESTful web services. Persistence is achieved using the object-relational mapping (ORM) library Hibernate. Data is stored in a MySQL database. Maven is used for build automation. Testing was done using Postman.

## File Structure
```
├── .settings    # project settings and workspace preferences for eclipse
└── src/main
|   └── java
|   |   └── io/johnsell620/jMessage
|   |       └── dao
|   |       |   └── HybernateUtil.java
|   |       ├── exception
|   |       |   ├── DataNotFoundException.java
|   |       |   ├── DataNotFoundExceptionMapper.java
|   |       |   └── GenericExceptionMapper.java
|   |       ├── model
|   |       |   ├── Comment.java
|   |       |   ├── CustomCreatedDate.java
|   |       |   ├── ErrorMessage.java
|   |       |   ├── JsonDateSerializer.java
|   |       |   ├── Link.java
|   |       |   ├── Message.java
|   |       |   ├── Profile.java
|   |       |   └── User.java
|   |       ├── resources
|   |       |   ├── CommentResource.java
|   |       |   ├── DateMessageBodyWriter.java
|   |       |   ├── LinkResource.java
|   |       |   ├── MessageResource.java
|   |       |   ├── ProfileResource.java
|   |       |   └── UserResource.java
|   |       ├── rest
|   |       |   ├── LoggingFilter.java
|   |       |   ├── PoweredByResponseFilter.java
|   |       |   ├── SecuredResource.java
|   |       |   └── SecurityFilter.java
|   |       └── service
|   |           ├── CommentService.java
|   |           ├── LinkService.java
|   |           ├── MessageService.java
|   |           ├── ProfileService.java
|   |           ├── ServiceUtil.java
|   |           └── UserService.java
|   └── webapp
|       ├── index.jsp
|       └── WEB-INF
|           └── web.xml
├── .classpath
├── .gitignore
├── .project
├── README.md
└── pom.xml
```

## Maven Dependency Tree:
```
--- maven-dependency-plugin:2.8:tree (default-cli) @ jMessage ---
io.johnsell620:jMessage:war:0.0.1-SNAPSHOT
+- org.glassfish.jersey.containers:jersey-container-servlet-core:jar:2.23:compile
|  +- org.glassfish.hk2.external:javax.inject:jar:2.4.0-b34:compile
|  +- org.glassfish.jersey.core:jersey-common:jar:2.23:compile
|  |  +- javax.annotation:javax.annotation-api:jar:1.2:compile
|  |  +- org.glassfish.jersey.bundles.repackaged:jersey-guava:jar:2.23:compile
|  |  +- org.glassfish.hk2:hk2-api:jar:2.4.0-b34:compile
|  |  |  +- org.glassfish.hk2:hk2-utils:jar:2.4.0-b34:compile
|  |  |  \- org.glassfish.hk2.external:aopalliance-repackaged:jar:2.4.0-b34:compile
|  |  +- org.glassfish.hk2:hk2-locator:jar:2.4.0-b34:compile
|  |  \- org.glassfish.hk2:osgi-resource-locator:jar:1.0.1:compile
|  +- org.glassfish.jersey.core:jersey-server:jar:2.23:compile
|  |  +- org.glassfish.jersey.core:jersey-client:jar:2.23:compile
|  |  +- org.glassfish.jersey.media:jersey-media-jaxb:jar:2.23:compile
|  |  \- javax.validation:validation-api:jar:1.1.0.Final:compile
|  \- javax.ws.rs:javax.ws.rs-api:jar:2.0.1:compile
+- org.glassfish.jersey.media:jersey-media-json-jackson:jar:2.23.2:compile
|  +- org.glassfish.jersey.ext:jersey-entity-filtering:jar:2.23:compile
|  +- com.fasterxml.jackson.jaxrs:jackson-jaxrs-base:jar:2.5.4:compile
|  |  +- com.fasterxml.jackson.core:jackson-core:jar:2.5.4:compile
|  |  \- com.fasterxml.jackson.core:jackson-databind:jar:2.5.4:compile
|  +- com.fasterxml.jackson.jaxrs:jackson-jaxrs-json-provider:jar:2.5.4:compile
|  |  \- com.fasterxml.jackson.module:jackson-module-jaxb-annotations:jar:2.5.4:compile
|  \- com.fasterxml.jackson.core:jackson-annotations:jar:2.5.4:compile
+- org.hibernate:hibernate-core:jar:5.1.10.Final:compile
|  +- org.jboss.logging:jboss-logging:jar:3.3.0.Final:compile
|  +- org.hibernate.javax.persistence:hibernate-jpa-2.1-api:jar:1.0.0.Final:compile
|  +- org.javassist:javassist:jar:3.20.0-GA:compile
|  +- antlr:antlr:jar:2.7.7:compile
|  +- org.apache.geronimo.specs:geronimo-jta_1.1_spec:jar:1.1.1:compile
|  +- org.jboss:jandex:jar:2.0.3.Final:compile
|  +- com.fasterxml:classmate:jar:1.3.0:compile
|  +- dom4j:dom4j:jar:1.6.1:compile
|  |  \- xml-apis:xml-apis:jar:1.0.b2:compile
|  \- org.hibernate.common:hibernate-commons-annotations:jar:5.0.1.Final:compile
+- mysql:mysql-connector-java:jar:5.1.44:compile
+- net.sf.ehcache:ehcache:jar:2.10.4:compile
|  \- org.slf4j:slf4j-api:jar:1.7.7:compile
+- org.hibernate:hibernate-ehcache:jar:5.1.10.Final:compile
\- commons-logging:commons-logging:jar:1.2:compile
```

## Development
1. Clone the repository and import into eclipse IDE.
2. Create the MySQL database with the following SQL code.
```
CREATE DATABASE `jMessage` /*!40100 DEFAULT CHARACTER SET utf8 */;
CREATE TABLE `comments` (
 `commentId` bigint(20) NOT NULL,
 `author` varchar(255) DEFAULT NULL,
 `created` datetime DEFAULT NULL,
 `message` varchar(255) DEFAULT NULL,
 `messageId` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`commentId`),
 KEY `FKbsb2wy0u2qx2s0ersm2dpgx73` (`messageId`),
 CONSTRAINT `FKbsb2wy0u2qx2s0ersm2dpgx73` FOREIGN KEY (`messageId`) REFERENCES `messages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `links` (
 `linkId` bigint(20) NOT NULL,
 `link` varchar(255) DEFAULT NULL,
 `messageId` bigint(20) DEFAULT NULL,
 `rel` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`linkId`),
 KEY `FK2eiob8y2q5kusmqhiden5d5pm` (`messageId`),
 CONSTRAINT `FK2eiob8y2q5kusmqhiden5d5pm` FOREIGN KEY (`messageId`) REFERENCES `messages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `messages` (
 `id` bigint(20) NOT NULL,
 `author` varchar(255) DEFAULT NULL,
 `created` datetime DEFAULT NULL,
 `message` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `profiles` (
 `profileId` bigint(20) NOT NULL AUTO_INCREMENT,
 `created` datetime DEFAULT NULL,
 `firstname` varchar(255) DEFAULT NULL,
 `lastname` varchar(255) DEFAULT NULL,
 `profilename` varchar(255) DEFAULT NULL,
 `user_id` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`profileId`),
 KEY `FK410q61iev7klncmpqfuo85ivh` (`user_id`),
 CONSTRAINT `FK410q61iev7klncmpqfuo85ivh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
CREATE TABLE `users` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `pId` bigint(20) NOT NULL,
 `password` varchar(255) DEFAULT NULL,
 `username` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
```
3. Start server.
4. Test.

## References
 - https://docs.oracle.com/javaee/7/tutorial/jaxrs.htm
 - https://javabrains.io/courses/javaee_jaxrs
 - https://docs.jboss.org/hibernate/orm/3.3/reference/en-US/html/tutorial.html
 - https://spring.io/guides/gs/consuming-rest-angularjs
