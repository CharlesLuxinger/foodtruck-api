FROM openjdk:13-alpine

RUN apk add bash

RUN apk add --no-cache openssl

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

COPY "./target/foodtruck-api-0.0.1.jar" /usr/src/foodtruck-api/

WORKDIR /usr/src/foodtruck-api

EXPOSE 8080

CMD ["java", "-jar", "foodtruck-api-0.0.1.jar"]