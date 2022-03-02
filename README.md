## SPRINGBOOT-JAVA17-DOCKER Branch Updates ##
- Project migrated to Spring Boot.
- Database, API, and client layers containerized using Docker.

### Steps for development startup ###

#### 1. Build MySQL database image, copy development database jmessage, and give all users read permission to mysql volume.
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
Note this step can be skipped, but when running ```docker-compose up --build``` for the first time, the MySQL container will need to be restarted manually before the API container times out from database connection failure. Otherwise just run ```docker-compose up``` after the first command and the containers will start up fine.

#### 2. Build api package (.jar).
Do this with the database container running to avoid failed tests during packaging.
```bash
$ ./mvnw package
```

#### 3. Build (if needed) and run database, backend, and frontend containers. Don't recreate database container.
```bash
$ docker-compose up --no-recreate
```

