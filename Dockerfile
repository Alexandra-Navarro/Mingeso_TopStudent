FROM openjdk:17
ARG JAR_FILE=target/TopEducation1.jar
COPY ${JAR_FILE} TopEducation1.jar
ENTRYPOINT ["java","-jar","/TopEducation1.jar"]