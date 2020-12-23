## Overview
Web messaging application with React frontend and RESTful backend service built on OpenJDK 11. Jersey library used to implement the JAX-RS API for RESTful web services. Data stored in MySQL database, persisted using the Hibernate ORM library. Maven used for build automation. Testing done using Postman.

Separation of frontend and backend in this way ensures good testability of the application.

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
|   └── ui
|   |   ├── public
|   |   |   └── index.html
|   |   └── src
|   |       └── features
|   |           ├── conversation
|   |           ├── thread
|   |           ├── user
|   |           ├── App.js
|   |           └── index.js
|   └── webapp
|   |   ├── index.jsp
|   |   └── WEB-INF
|   |       └── web.xml
|   └── tests
|       ├── automated
|       |   ├── test-result
|       |   |   └── results.xml
|       |   ├── test  
|       |   |   ├── user_test.js
|       |   |   └── profile_test.js
|       |   ├── Dockerfile
|       |   ├── entrypoint.sh
|       |   └── package.json
|       └── postman
|           ├── GET_message.js
|           ├── GET_profile.js
|           ├── GET_users.js
|           ├── POST_message.js
|           └── jMessage.postman_test_run.json
|
├── .classpath
├── .gitignore
├── .project
├── LICENSE
├── README.md
└── pom.xml
```

## Development
1. Clone the repository and import into eclipse IDE.
2. Create the MySQL database with the following SQL code. **NB:** Only the first SQL statement is required since the tables will be updated automatically with Hibernate.
```sql
CREATE DATABASE `jMessage` /*!40100 DEFAULT CHARACTER SET utf8 */
USE `jMessage;

CREATE TABLE `users` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT,
 `pId` bigint(20) NOT NULL,
 `password` varchar(255) DEFAULT NULL,
 `username` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `profiles` (
 `profileId` bigint(20) NOT NULL AUTO_INCREMENT,
 `created` datetime DEFAULT NULL,
 `firstName` varchar(255) DEFAULT NULL,
 `lastName` varchar(255) DEFAULT NULL,
 `profileName` varchar(255) DEFAULT NULL,
 `user_id` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`profileId`),
 KEY `FK-uniqueID` (`user_id`),
 CONSTRAINT `FK-uniqueKey` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `messages` (
 `id` bigint(20) NOT NULL,
 `author` varchar(255) DEFAULT NULL,
 `created` datetime DEFAULT NULL,
 `message` varchar(255) DEFAULT NULL,
 `profileId` bigint(20) NOT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comments` (
 `commentId` bigint(20) NOT NULL,
 `author` varchar(255) DEFAULT NULL,
 `created` datetime DEFAULT NULL,
 `message` varchar(255) DEFAULT NULL,
 `messageId` bigint(20) DEFAULT NULL,
 PRIMARY KEY (`commentId`),
 KEY `FK-uniqueKey` (`messageId`),
 CONSTRAINT `FK-uniqueKey` FOREIGN KEY (`messageId`) REFERENCES `messages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `links` (
 `linkId` bigint(20) NOT NULL,
 `link` varchar(255) DEFAULT NULL,
 `messageId` bigint(20) DEFAULT NULL,
 `rel` varchar(255) DEFAULT NULL,
 PRIMARY KEY (`linkId`),
 KEY `FK-uniqueKey` (`messageId`),
 CONSTRAINT `FK-uniqueKey` FOREIGN KEY (`messageId`) REFERENCES `messages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
3. Start server.
4. Test.

## References
 - https://docs.oracle.com/javaee/7/tutorial/jaxrs.htm
 - https://javabrains.io/courses/javaee_jaxrs
 - https://docs.jboss.org/hibernate/orm/3.3/reference/en-US/html/tutorial.html
