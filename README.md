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
