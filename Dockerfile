## Execute application
FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=solicitudes/target/solicitudes-0.0.1.jar
COPY ${JAR_FILE} app_cfiet.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_cfiet.jar"]