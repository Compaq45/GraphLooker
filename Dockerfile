#FROM openjdk:17-oracle
#ARG JAR_FILE=target/GraphLooker_jar/GraphLooker.jar
#WORKDIR /opt/app
#EXPOSE 8080
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","app.jar"]

FROM openjdk:17-oracle as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract


FROM openjdk:17-oracle
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]