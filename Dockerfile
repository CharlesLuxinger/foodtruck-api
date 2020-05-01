FROM maven:3.6.3-jdk-11-slim AS build

WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /build/src/
RUN mvn package -B

FROM openjdk:11.0.7-jre-slim AS release

COPY --from=build /build/target/*.jar /app.jar

CMD ["java", "-jar", "/app.jar"]