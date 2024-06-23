# Etap 1: Budowanie aplikacji
FROM maven:latest AS build

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie pliku pom.xml i pobranie zależności bez budowania
COPY pom.xml ./
RUN mvn dependency:go-offline -B

# Kopiowanie całego projektu i budowanie aplikacji
COPY src ./src
RUN mvn clean package -DskipTests

# Etap 2: Tworzenie obrazu uruchomieniowego
FROM openjdk:21-jdk-slim

# Ustawienie katalogu roboczego
WORKDIR /app

# Kopiowanie artefaktu z etapu budowania
COPY --from=build /app/target/*.jar ./app.jar

# Określenie punktu wejściowego
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]

# Określenie portu, na którym będzie działać aplikacja
EXPOSE 8080
