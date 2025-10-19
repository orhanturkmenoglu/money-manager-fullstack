# 1. Build aşaması
FROM maven:3.9.0-eclipse-temurin-24 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# 2. Run aşaması
FROM openjdk:24-jdk-slim
WORKDIR /app
COPY --from=build /app/target/money-manager-0.0.1-SNAPSHOT.jar ./money-manager.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "money-manager.jar"]
