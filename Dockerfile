FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /src
COPY . .
RUN mvn clean install -Dmaven.test.skip

FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY --from=build /src/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]