FROM openjdk:17-jdk-slim

WORKDIR /app


COPY target/vinconomy-0.0.1-SNAPSHOT.jar vinconomy_server.jar

# Actually useless - just to telegraph what port is being utilized by the service
EXPOSE 8080

ENTRYPOINT ["java","-jar","vinconomy_server.jar"]