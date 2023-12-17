FROM openjdk:17.0.1-jdk-slim

ARG JAR_FILE=target/*SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} waterbot.jar

ENTRYPOINT ["java","-jar","waterbot.jar"]
