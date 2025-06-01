# Stage 1: Build the JAR using Maven
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copy Maven wrapper and project files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
COPY src ./src

# Build the JAR
RUN ./mvnw clean install -DskipTests

# Stage 2: Create final image with only the JAR
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy JAR from the build stage
COPY --from=builder /app/target/Tic-Tac-Toe-Backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port (just documentation)
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
