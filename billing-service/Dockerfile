# Stage 1: Build the billing service
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Create minimal runtime image
FROM openjdk:21-jdk-slim
WORKDIR /app
COPY --from=build /app/target/billing-service-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]