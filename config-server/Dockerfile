FROM openjdk:11.0-jre
ADD ./target/config-server-0.0.1-SNAPSHOT.jar config-server-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/config-server-0.0.1-SNAPSHOT.jar"]
EXPOSE 8888