# Use OpenJDK base image
FROM openjdk:17-jdk-slim

# Add a label
LABEL maintainer="khadar"

# Set working directory inside the container
WORKDIR /app

# Copy the built jar from local to container
COPY target/krfootwear-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8282

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
