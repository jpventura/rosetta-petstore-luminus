.PHONY: all build clean help push run

build:
	@docker build --tag novo-connection/hermes:latest .

clean:
	@docker image rm --force novo-connection/hermes:latest
	@docker image rm --force 415122598192.dkr.ecr.us-west-2.amazonaws.com/hermes:latest

push:
	@docker tag novo-connection/hermes:latest 415122598192.dkr.ecr.us-west-2.amazonaws.com/novo-connection/hermes:latest
	@docker push 415122598192.dkr.ecr.us-west-2.amazonaws.com/novo-connection/hermes:latest

help:
	@echo ''
	@echo 'Usage: make [TARGET] [EXTRA_ARGUMENTS]'
	@echo ''
	@echo 'Targets:'
	@echo '  build    	build docker image'
	@echo '  clean    	remove docker image'
	@echo '  shell    	run container shell'
	@echo ''
