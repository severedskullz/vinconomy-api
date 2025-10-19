VINCONOMY_DB_NAME='name';
VINCONOMY_DB_PASS='password';
VINCONOMY_DB_USR='user';
VINCONOMY_DB_ROOT_PASS='root_password';
VINCONOMY_INTERNAL_SERVER_PORT=8080;
# leftmost value - external port (for you to access from localhost/ with IP), rightmost value - internal port (for inter-service communication inside docker network).
# make sure internal port is the same as VINCONOMY_INTERNAL_SERVER_PORT
VINCONOMY_SERVER_DOCKER_PORT_PAIR='8081:8080';
# for this one rightmost part must stay 3306, unless you run MYSQL with very custom configuration and it deploys on a different port by default
VINCONOMY_DB_DOCKER_PORT_PAIR='3307:3306';

docker-compose up
