FROM eclipse-temurin:21-jdk-jammy
COPY . .
RUN ./gradlew bootJar -x test --no-daemon
ENV SPRING_PROFILES_ACTIVE=docker
CMD ["java", "-jar", "build/libs/bakery-0.0.1-SNAPSHOT.jar"]