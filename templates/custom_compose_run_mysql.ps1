$env:VINCONOMY_DB_NAME="name";
$env:VINCONOMY_DB_PASS="password";
$env:VINCONOMY_DB_USR="user";
$env:VINCONOMY_DB_ROOT_PASS="root_password";
# leftmost value - external port (for you to access from localhost/ with IP), rightmost value - internal port (for inter-service communication inside docker network).
# for this one rightmost part must stay 3306, unless you run MYSQL with very custom configuration and it deploys on a different port by default
$env:VINCONOMY_DB_DOCKER_PORT_PAIR="3307:3306";

try {
    docker-compose -f mysql\docker-compose.db.yml up
}
finally {
    Remove-Item Env:VINCONOMY_DB_NAME, Env:VINCONOMY_DB_PASS, Env:VINCONOMY_DB_USR, Env:VINCONOMY_DB_ROOT_PASS, Env:VINCONOMY_DB_DOCKER_PORT_PAIR -ErrorAction SilentlyContinue
}