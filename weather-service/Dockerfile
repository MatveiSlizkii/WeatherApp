FROM openjdk:17-oracle
MAINTAINER Matty

ADD ./target/weather-service.jar /app/

CMD ["java", "-Xmx200m", "-jar", "/app/weather-service.jar"]

EXPOSE 8080 8089

