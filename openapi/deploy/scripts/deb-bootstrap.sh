#!/usr/bin/env bash


function deb-bootstrap() {
    apt-get update

    apt-get install --no-install-recommends --yes \
        apt-utils

    if [ -z "${CI}" ]
    then
      apt-get upgrade --yes
    fi

    apt-get install --no-install-recommends --yes \
        clojure \
        curl \
        httpie \
        leiningen \
	    mariadb-client \
	    vim \
        wget

    if [ -z "${CI}" ]
    then
        apt-get autoremove --purge
        apt-get autoclean
        rm -rfv /var/cache/apt/archives/*
    fi
}

deb-bootstrap
