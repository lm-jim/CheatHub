FROM maven:3.8.4-openjdk-17
WORKDIR /project
COPY src /project/src
COPY pom.xml /project/
RUN mvn -B package -DskipTests
EXPOSE 8443
CMD ["java", "-jar", "target/CheatHub-0.0.1-SNAPSHOT.jar"]
