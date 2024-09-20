FROM openjdk:17-oracle
ARG JAR_FILE=target/artifacts/GraphLooker_jar/GraphLooker.jar
WORKDIR /opt/app
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]