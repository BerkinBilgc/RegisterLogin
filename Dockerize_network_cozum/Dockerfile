FROM adoptopenjdk/openjdk11
ARG JAR_FILE=TechCareerAirtiesRegisterLogin-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} register
ENTRYPOINT [ "java","-jar","/register" ]
