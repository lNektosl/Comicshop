FROM maven:3.8.3-openjdk-17 as builder
WORKDIR /src
COPY . .
RUN mvn clean instal -Dmaven.test.skip
