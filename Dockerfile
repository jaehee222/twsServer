FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew build

CMD ["sh", "-c", "java -jar build/libs/twsServer-0.0.1-SNAPSHOT.jar"]