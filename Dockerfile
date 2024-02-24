FROM openjdk:latest
COPY build/libs/qonaq-ol-0.0.1-SNAPSHOT.jar .
EXPOSE 8081
CMD ["java", "-jar", "qonaq-ol-0.0.1-SNAPSHOT.jar"]