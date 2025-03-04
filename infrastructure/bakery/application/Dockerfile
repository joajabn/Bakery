FROM eclipse-temurin:21-jdk-jammy
COPY . .
RUN ./gradlew bootJar --no-daemon
ENV SPRING_PROFILES_ACTIVE=local
CMD ["java", "-jar", "build/libs/bakery-0.0.1-SNAPSHOT.jar"]