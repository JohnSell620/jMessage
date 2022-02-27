## jMessage REST API ##

```bash
$ docker run \
    -d \
    --network=host \
    -e MYSQL_DATABASE="jmessage" \
    -e MYSQL_ROOT_PASSWORD="[somepassword]" \
    -v ${PWD}/api/mysql:/var/lib/mysql \
    -v ${PWD}/api/dump.sql:/docker-entrypoint-initdb.d/1-dump.sql \
    mysql:5.7-debian
$ docker run -d --network=host openjdk:17-alpine-jmessage-api
```