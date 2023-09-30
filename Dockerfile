FROM maven:3.8.3-openjdk-17
WORKDIR /src
COPY . .
RUN mvn clean install -Dmaven.test.skip