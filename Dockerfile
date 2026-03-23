FROM bellsoft/liberica-openjdk-alpine:21 AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

RUN ./gradlew --no-daemon dependencies

COPY src src
RUN ./gradlew --no-daemon build -x test

FROM bellsoft/liberica-openjre-alpine:21
WORKDIR /app

COPY --from=build /app/build/libs/*.jar manease.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx512m", "-jar", "manease.jar"]