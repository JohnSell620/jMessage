## SPRINGBOOT-JAVA17-DOCKER Branch Updates ##
- Project migrated to Spring Boot.
- Database, API, and client layers containerized using Docker.

### Steps for development startup ###

1. Build MySQL database images and create jmessage database. Copy database using sql dump if needed. Finally give read permission to all users.
```bash
$ docker run \
    -d \
    -p 3306:3306 \
    -e MYSQL_DATABASE="jmessage" \
    -e MYSQL_ROOT_PASSWORD="somepassword" \
    -v ${PWD}/api/mysql:/var/lib/mysql \
    -v ${PWD}/api/dump.sql:/docker-entrypoint-initdb.d/1-dump.sql \
    mysql:5.7-debian
$ mysql -uroot -psomepassword -h127.0.0.1 -P3306 jmessage < /path/to/jmessage.sql
$ sudo chmod -R a+r mysql
```

2. Build api package (.jar).
```bash
$ ./mvnw package
```

3. Build (if needed) and run database, backend, and frontend containers. Don't recreate database container.
```bash
$ docker-compose up --no-recreate
```