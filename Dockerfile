#Build the Maven project
FROM openjdk:8-jdk-alpine as builder
COPY . /usr/src/app
WORKDIR /usr/src/app
RUN ./mvnw package

#Build the Tomcat container
FROM openjdk:8-jdk-alpine
RUN apk update
RUN apk add zip

# Copy fhirFilter war file to webapps.
COPY --from=builder /usr/src/app/target/decedent-index.jar decedent-index.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","/decedent-index.jar"]