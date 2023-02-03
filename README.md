![Coverage](.github/badges/jacoco.svg)
![Branches](.github/badges/branches.svg)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ricardo-campos-org_onde-tem-vagas-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ricardo-campos-org_onde-tem-vagas-api)

# fastvagas-java
[Side Project] Service to find jobs published openly on the web. This service do crawler to find jobs and parse HTML.

# Tech stack
- [x] Back-end
- [x] Front-end
- [x] Docker
- [x] Database related
- [x] Testing
- [x] Maven
- [x] NPM
- [x] Java related
- [ ] JavaScript/TypeScript/React related
- [ ] Shell scripting

## Requirements

- Java OpenJDK 17 (I recommend [sdkman](https://sdkman.io/install))
- Maven (You can use sdkman)
- Docker

## Suggestions

- Postman
- DBeaver

## Running

You can get the service running with `./mvnw spring-boot:run`. But you
need the database running to be able to run the service. You can start
the database in a Docker container with `docker run -t -i -p 5432:5432
-e POSTGRES_USER=$DATASOURCE_USER -e POSTGRES_PASSWORD=$DATASOURCE_PASS
-e POSTGRES_DB=$DATASOURCE_NAME -v data:/var/lib/postgresql/fastdata postgres:14.6-bullseye`

The server should start at the port 8080

## Running with Docker

Build:
```bash
docker build -t ghcr.io/ricardo-campos-org/fast-jobs:1.0 .
```

Remember you need a Postgres instance up and running!
```bash
docker run -d -p 5432:5432 \
  --name fast-jobs-postgres \
  -e POSTGRES_USER=${DATASOURCE_USER} \
  -e POSTGRES_DB=${DATASOURCE_NAME} \
  -e POSTGRES_PASSWORD=${DATASOURCE_PASS} \
  postgres:13.9-bullseye
```

Running:
```bash
docker run -t -i --net=host \
  --name fast-jobs \
  --env-file .env \
  ghcr.io/ricardo-campos-org/fast-jobs:1.0
```

Running with Docker Compose:
```bash
docker-compose up --build
```

Cleaning up Docker Compose:
```bash
docker-compose down --remove-orphans
```
## Tests

You can run all tests with:
```bash
./mvnw --no-transfer-progress clean verify test
```

## Checkstyle

And before making any changes, make sure the checkstyle is passing
```bash
./mvnw --no-transfer-progress checkstyle:checkstyle -Dskip.checkstyle=false
```
