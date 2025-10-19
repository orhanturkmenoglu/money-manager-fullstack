# Temel imaj olarak OpenJDK 24 slim kullanıyoruz
FROM openjdk:24-jdk-slim

# Çalışma dizini
WORKDIR /app

# Jar dosyasını kopyala (target altından)
COPY target/money-manager-0.0.1-SNAPSHOT.jar ./money-manager.jar

# Port aç
EXPOSE 8080

# Uygulamayı çalıştır
ENTRYPOINT ["java", "-jar", "money-manager.jar"]
