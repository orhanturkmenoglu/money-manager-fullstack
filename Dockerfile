# 1. Build stage: Maven ile jar oluştur
FROM maven:3.9.3-eclipse-temurin-24 AS build
WORKDIR /app

# Pom ve kaynak kodları kopyala
COPY pom.xml .
COPY src ./src

# Jar dosyasını build et
RUN mvn clean package -DskipTests

# 2. Runtime stage: OpenJDK ile çalıştır
FROM openjdk:24-jdk-slim
WORKDIR /app

# Build stage'den jar'ı kopyala
COPY --from=build /app/target/money-manager-0.0.1-SNAPSHOT.jar ./money-manager.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "money-manager.jar"]
