# Use uma imagem base do JDK
FROM openjdk:17-jdk-slim

VOLUME /tmp

WORKDIR /app

COPY target/votacao-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
