# Step 1: Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/emp_backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that the Spring Boot application will run on (default is 8080)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
