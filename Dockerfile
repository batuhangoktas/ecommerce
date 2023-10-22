FROM openjdk:17-slim as build
ARG JAR_FILE=target/ecommerce-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]