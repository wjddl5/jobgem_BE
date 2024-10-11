FROM gradle:7.6-jdk as builder

WORKDIR /app
COPY . ./
RUN gradle clean build --no-daemon

FROM openjdk:17-alpine
WORKDIR /app
COPY --from=builder /app/build/libs/jobgem-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar","jobgem-0.0.1-SNAPSHOT.jar"]
