FROM maven:3.6.1-jdk-11 AS MAVEN_TOOL_CHAIN_CONTAINER
RUN mkdir src
COPY src /tmp/src
COPY ./pom.xml /tmp/
WORKDIR /tmp/
RUN mvn package
RUN ls -la /tmp

FROM bellsoft/liberica-openjdk-alpine:11
COPY --from=MAVEN_TOOL_CHAIN_CONTAINER /tmp/target/http_server-1.0-SNAPSHOT-jar-with-dependencies.jar /tmp/
WORKDIR /tmp/
ENTRYPOINT ["java","-jar", "http_server-1.0-SNAPSHOT-jar-with-dependencies.jar"]