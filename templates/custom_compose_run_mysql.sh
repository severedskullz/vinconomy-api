#!/usr/bin/env bash

VINCONOMY_DB_NAME='name'
VINCONOMY_DB_PASS='password'
VINCONOMY_DB_USR='user'
VINCONOMY_DB_ROOT_PASS='root_password'
# leftmost value - external port (for you to access from localhost/ with IP), rightmost value - internal port (for inter-service communication inside docker network).
# for this one rightmost part must stay 3306, unless you run MYSQL with very custom configuration and it deploys on a different port by default
VINCONOMY_DB_DOCKER_PORT_PAIR='3307:3306'

docker-compose -f ./mysql/docker-compose.db.yml up