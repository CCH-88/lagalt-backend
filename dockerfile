FROM gradle:jdk17 AS gradle
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon --info -x test

FROM eclipse-temurin:17-jdk-jammy as runtime

EXPOSE 8080

RUN mkdir /app

COPY --from=gradle /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]
