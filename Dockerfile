# Use an official Maven/Java as a parent image
FROM maven:3.8.4-openjdk-17 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the project Object Model (POM) file to the container
COPY pom.xml .

# Copy the Maven Wrapper files (if needed)
# COPY .mvn .mvn

# Copy the application source code to the container
COPY src src

# Build the application with Maven
RUN mvn clean install -DskipTests

# Use a larger base image with Java 17 for the final image
FROM openjdk:17

# Set the working directory in the final image
WORKDIR /app

# Copy the JAR file from the builder stage to the final image
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the JAR file
CMD ["java", "-jar", "app.jar"]

