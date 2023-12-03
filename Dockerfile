#FROM postgres
#ENV POSTGRES_USER=postgres
#ENV POSTGRES_PASSWORD=1
#ENV POSTGRES_DB=Comic_shop

FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /app
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests

FROM maven:3.8.3-openjdk-17
COPY --from=build /usr/src/app/target/Comicshop-0.0.1-SNAPSHOT.jar Comicshop-0.0.1-SNAPSHOT.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","Comicshop-0.0.1-SNAPSHOT.jar"]

