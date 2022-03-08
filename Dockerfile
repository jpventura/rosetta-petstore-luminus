FROM ubuntu:22.04

RUN mkdir -p /opt/brasil-paralelo

COPY ./deploy/scripts /opt/brasil-paralelo/scripts

RUN /opt/brasil-paralelo/scripts/deb-bootstrap.sh

COPY project.clj /opt/brasil-paralelo/
COPY ./*.edn /opt/brasil-paralelo/
COPY target/uberjar/brasilparalelo.jar /opt/brasil-paralelo/app.jar

WORKDIR /opt/brasil-paralelo
# RUN /opt/brasil-paralelo/scripts/bootstrap.sh

EXPOSE ${PORT:-3000}

CMD ["java", "-jar", "/opt/brasil-paralelo/app.jar"]
