FROM openjdk:17
MAINTAINER AndersonHsieh
RUN mkdir app
WORKDIR /app
COPY target/production-ticket-service-0.0.1.jar app/production-ticket-service-0.0.1.jar
ENTRYPOINT ["java","-jar","app/production-ticket-service-0.0.1.jar"]
EXPOSE 8087
EXPOSE 9097