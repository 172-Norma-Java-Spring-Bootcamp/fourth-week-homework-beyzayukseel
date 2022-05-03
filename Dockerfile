FROM openjdk:18-jdk-alpine
MAINTAINER beyzanuryuksel
COPY target/order-example-0.0.1-SNAPSHOT.jar order-example.jar
ENTRYPOINT ["java","-jar","order-example.jar"]
EXPOSE 8084