# Use the official maven/Java 8 image to create a build artifact.
# https://hub.docker.com/_/maven
FROM gradle:8.4-jdk17-alpine AS build
#FROM openjdk:17-jdk-slim as build
WORKDIR /code
#
# # Copy local code to the container image.
COPY . .
#
# # Build a release artifact.
RUN gradle clean build --no-daemon -x test


#
# Package stage
#
# It's important to use OpenJDK 8u191 or above that has container support enabled.
# https://hub.docker.com/r/adoptopenjdk/openjdk8
# https://docs.docker.com/develop/develop-images/multistage-build/#use-multi-stage-builds
FROM openjdk:21-jdk-slim

WORKDIR /app
# Copy the jar to the production image from the builder stage.
COPY --from=build /code/build/libs/method-playground-0.0.1-SNAPSHOT.jar app.jar

# ENV PORT=8080
ARG APP_METHOD_API_TOKEN
ENV APP_METHOD_API_TOKEN $APP_METHOD_API_TOKEN
EXPOSE 8080

# Run the web service on container startup.
ENTRYPOINT ["java","-jar","app.jar"]