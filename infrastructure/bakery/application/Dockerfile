FROM eclipse-temurin:21-jdk-jammy
COPY . .
RUN ./gradlew bakery:bootJar --no-daemon
CMD ["java", "-jar", "bakery/build/libs/bakery-0.0.1-SNAPSHOT.jar"