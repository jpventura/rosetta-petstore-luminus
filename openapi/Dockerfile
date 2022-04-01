FROM ubuntu:22.04

ARG HOST_USER=${HOST_USER}

ADD ./ /opt/brasil-paralelo

WORKDIR /opt/brasil-paralelo

RUN deploy/scripts/deb-bootstrap.sh
RUN deploy/scripts/bootstrap.sh

USER ${HOST_USER}

ENTRYPOINT [ "/usr/bin/lein" ]

EXPOSE ${PORT:-3000}
