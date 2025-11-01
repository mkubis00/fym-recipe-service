FROM openjdk:21-slim

LABEL org.opencontainers.image.authors="mkvbs.com"

COPY build/libs/recipe-service-1.0.1-SNAPSHOT.jar recipe-service-1.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "recipe-service-1.0.1-SNAPSHOT.jar"]