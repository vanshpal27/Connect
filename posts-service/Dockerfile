FROM maven:3.9.4-eclipse-temurin-21-alpine

WORKDIR /app
#COPY ./target/dockerlearn-0.0.1-SNAPSHOT.jar .
#ENTRYPOINT ["java","-jar","dockerlearn-0.0.1-SNAPSHOT.jar"]

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw","spring-boot:run"]
# Build stage
#FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
#
#WORKDIR /app
#COPY pom.xml .
#COPY .mvn .mvn
#COPY mvnw .
#RUN ./mvnw dependency:go-offline
#
#COPY src ./src
#RUN ./mvnw clean package -DskipTests
#
## Run stage
#FROM eclipse-temurin:21-jdk-alpine
#
#WORKDIR /app
#COPY --from=build /app/target/*.jar app.jar
#
#ENTRYPOINT ["java","-jar","app.jar"]
