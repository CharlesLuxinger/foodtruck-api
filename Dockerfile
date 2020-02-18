FROM openjdk:13-alpine

RUN apk add bash

COPY "./target/foodtruck-api-0.0.1.jar" /usr/src/foodtruck-api/

WORKDIR /usr/src/foodtruck-api

EXPOSE 8080

CMD ["java", "-jar", "foodtruck-api-0.0.1.jar"]