FROM openjdk:17-alpine
RUN apk update && apk add mysql-client && rm -f /var/cache/apk/*
COPY target/*.jar jMessage-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/jMessage-0.0.1-SNAPSHOT.jar"]