FROM openjdk:8-jdk-alpine
MAINTAINER prodet.org
COPY target/springcms-0.0.4-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]