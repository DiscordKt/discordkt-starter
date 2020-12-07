FROM gradle:6.7.0-jdk15 AS build
COPY --chown=gradle:gradle . /starter
WORKDIR /starter
RUN gradle shadowJar --no-daemon

FROM openjdk:8-jre-slim
RUN mkdir /config/
COPY --from=build /starter/build/libs/*.jar /

ENTRYPOINT ["java", "-jar", "/Starter.jar"]