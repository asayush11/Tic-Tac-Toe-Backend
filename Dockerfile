# Use a lightweight JDK 21 image
FROM eclipse-temurin:21-jdk

# Set working directory in container
WORKDIR /app

# Copy the executable JAR into the container
COPY target/Tic-Tac-Toe-Backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
