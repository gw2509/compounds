## Build stage
#FROM maven:3.8.2-jdk-8-slim AS build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean test package

# Create database population SQL file
FROM python:latest as db
COPY scripts/json2sql.py /usr/local/share
COPY data/schema.json /usr/local/share
COPY data/compounds.json /usr/local/share
CMD ["json2sql.py", "-flag"]

## Package stage
#FROM openjdk:8-jdk-alpine
#COPY --from=build /home/app/target/*.jar app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","app.jar"]

