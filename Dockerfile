FROM eclipse-temurin:17-jdk-alpine

EXPOSE 8080

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src

RUN ./mvnw clean package

RUN cp target/*.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]


