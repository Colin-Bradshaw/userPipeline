FROM openjdk:11.0.6-jre-slim

ARG DB-LOCATION
ENV DB-LOC = DB-LOCATION
ARG JAR_FILE=target/*.jar

COPY /target/springproject-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
