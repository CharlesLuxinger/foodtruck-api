FROM maven:3.6.3-jdk-13

COPY ${PWD} /usr/src/foodtruck-api

WORKDIR /usr/src/foodtruck-api

RUN mvn clean install

RUN apk add --no-cache openssl bash mysql-client

ENV DOCKERIZE_VERSION v0.6.1
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-alpine-linux-amd64-$DOCKERIZE_VERSION.tar.gz

EXPOSE 8080

CMD ["java", "-jar", "./target/foodtruck-api-0.0.1.jar"]