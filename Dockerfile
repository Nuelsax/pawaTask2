FROM openjdk:17
ADD  build/libs/pawaTask2-0.0.1-SNAPSHOT.jar pawatask2.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pawatask2.jar"]
