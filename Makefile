.PHONY: all

CMD_ARGUMENTS ?= $(cmd)

MAKE_FILENAME = $(lastword $(MAKEFILE_LIST))

PROJECT_NAME = $(notdir $(PWD))
SHELL=bash

# Note. If you change this, you also need to update docker-compose.yml.
# only useful in a setting with multiple services/ makefiles.
ENV_FILE ?= .env

# Note. If you change this, you also need to update docker-compose.yml.
# only useful in a setting with multiple services/ makefiles.SERVICE_TAARGET := brasil-paralelo
SERVICE_TARGET := openapi

ifeq ($(tag),)
TAG ?= latest
else
TAG ?= $(tag)
endif

ifeq ($(user),)
# USER retrieved from env, UID from shell.
HOST_USER ?= $(strip $(if $(USER),$(USER),admin))
HOST_UID ?= $(strip $(if $(shell id -u),$(shell id -u),1000))
else
# allow override by adding user= and/ or uid=  (lowercase!).
# uid= defaults to 0 if user= set (i.e. root).
HOST_USER = $(user)
HOST_UID = $(strip $(if $(uid),$(uid),0))
endif


help:
	@echo ''
	@echo 'Usage: make [TARGET] [EXTRA_ARGUMENTS]'
	@echo ''
	@echo 'Targets:'
	@echo '  build    	build docker image'
	@echo '  clean    	remove docker image'
	@echo '  shell    	run container shell'
	@echo ''

.environment: .env
	@eval $(sed -ne '/^export / {p;d}; /.*=/ s/^/export / p' $(ENV_FILE))

.uberjar: .env
	@echo "Backup Leiningen project definition"
	@cp $(PWD)/project.clj $(PWD)/project-$(TAG).clj
	@sed -i "s/\"[\.0-9]\+-SNAPSHOT\"/\"${TAG}\"/g" $(PWD)/project.clj

	@echo "Pack files and all dependencies into a jar file"
	@lein uberjar

	@docker-compose build $(SERVICE_TARGET)
	@echo "Restore Leiningen project definition"
	@cp $(PWD)/project-$(TAG).clj $(PWD)/project.clj
	@rm $(PWD)/project-$(TAG).clj

build: .uberjar

viado:
	@echo $(MAKE_FILENAME)
	@echo $(CMD_ARGUMENTS)

shell:
ifeq ($(CMD_ARGUMENTS),)
	docker-compose --project-name $(PROJECT_NAME).${HOSTNAME} run --rm $(SERVICE_TARGET) bash
else
	# run the command
	docker-compose -p $(PROJECT_NAME)_$(HOST_UID) run --rm $(SERVICE_TARGET) bash -c "$(CMD_ARGUMENTS)"
endif
