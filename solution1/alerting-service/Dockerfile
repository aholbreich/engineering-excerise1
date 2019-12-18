FROM openjdk:11-jre
MAINTAINER Alexander Holbreich <alexander@holbreich.org>
WORKDIR /app

ADD target/alerting-service-?.?.?.jar /app/service.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/service.jar"]