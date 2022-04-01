#!/usr/bin/env bash


function bootstrap() {
    useradd --create-home --home /home/${HOST_USER} ${HOST_USER}

    usermod -aG adm ${HOST_USER}

    chown root:adm -R /opt/brasil-paralelo
    chmod 0770 -R /opt/brasil-paralelo
    
    su ${HOST_USER} -c "cd /opt/brasil-paralelo && lein deps"
}

bootstrap
