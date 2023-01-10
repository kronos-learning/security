FROM openjdk:11

COPY . /app
WORKDIR /app

RUN ./gradlew build

EXPOSE 8080

CMD ["java", "-jar", "/app/build/libs/security-0.0.1-SNAPSHOT.jar"]