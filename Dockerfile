# ---- Build stage ----
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /workspace

# Cache dependencies
COPY pom.xml .
RUN mvn -q -B -DskipTests dependency:go-offline

# Copy the source and build
COPY src ./src
RUN mvn -q -B -DskipTests package

# ---- Runtime stage ----
FROM eclipse-temurin:21-jre AS runtime
WORKDIR /app

# Create non-root user
RUN useradd -m appuser
USER appuser

# Copy fat JAR
COPY --from=build /workspace/target/*.jar app.jar

# Cloud Run requires the app to listen on $PORT
ENV PORT=8080
EXPOSE 8080

# Spring Boot automatically respects -Dserver.port
ENTRYPOINT ["java","-Dserver.port=${PORT}","-jar","/app/app.jar"]