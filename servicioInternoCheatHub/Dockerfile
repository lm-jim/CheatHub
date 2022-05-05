FROM maven:3.8.4-openjdk-17
WORKDIR /project
COPY src /project/src
COPY pom.xml /project/
RUN mvn -B package
EXPOSE 8080
CMD ["java", "-jar", "target/servicioInternoCheatHub-0.0.1-SNAPSHOT.jar"]
