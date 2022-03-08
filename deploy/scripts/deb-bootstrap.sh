#!/usr/bin/env bash


function deb-bootstrap() {
    apt-get update

    apt-get install --no-install-recommends --yes \
        apt-utils

    # if [ -z "${CI}" ]
    # then
    #   apt-get upgrade --yes
    # fi

    apt-get install --no-install-recommends --yes \
        clojure \
        curl \
        httpie \
        leiningen \
	mariadb-client \
	vim \
        wget

    # if [ -z "${CI}" ]
    # then
    #     apt-get autoremove --purge
    #     apt-get autoclean
    #     rm -rfv /var/cache/apt/archives/*
    # fi

    GUEST_USER=${HOST_USER:-brasil-paralelo}

    useradd --create-home --home /home/${GUEST_USER} ${GUEST_USER}

    usermod -aG adm ${GUEST_USER}

    chown root:adm -R /opt/brasil-paralelo
    chmod 0770 -R /opt/brasil-paralelo
}

deb-bootstrap
