$env:VINCONOMY_DB_NAME="name";
$env:VINCONOMY_DB_PASS="password";
$env:VINCONOMY_DB_USR="user";
$env:VINCONOMY_DB_ROOT_PASS="root_password";
$env:VINCONOMY_INTERNAL_SERVER_PORT="8080";
# leftmost value - external port (for you to access from localhost/ with IP), rightmost value - internal port (for inter-service communication inside docker network).
# make sure internal port is the same as VINCONOMY_INTERNAL_SERVER_PORT
$env:VINCONOMY_SERVER_DOCKER_PORT_PAIR="8081:8080";
# for this one rightmost part must stay 3306, unless you run MYSQL with very custom configuration and it deploys on a different port by default
$env:VINCONOMY_DB_DOCKER_PORT_PAIR="3307:3306";

try {
    docker-compose up
}
finally {
    Remove-Item Env:VINCONOMY_DB_NAME, Env:VINCONOMY_DB_PASS, Env:VINCONOMY_DB_USR, Env:VINCONOMY_DB_ROOT_PASS, Env:VINCONOMY_INTERNAL_SERVER_PORT, Env:VINCONOMY_SERVER_DOCKER_PORT_PAIR, Env:VINCONOMY_DB_DOCKER_PORT_PAIR -ErrorAction SilentlyContinue
}