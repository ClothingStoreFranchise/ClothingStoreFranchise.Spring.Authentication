FROM adoptopenjdk/openjdk11:alpine-jre

EXPOSE 8083

ADD target/authentication.jar authentication.jar

ENTRYPOINT ["java", "-jar", "authentication.jar"]