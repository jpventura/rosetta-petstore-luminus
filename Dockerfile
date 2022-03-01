FROM openjdk:8-alpine

COPY target/uberjar/brasilparalelo.jar /opt/brasil-paralelo/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/opt/brasil-paralelo/app.jar"]
