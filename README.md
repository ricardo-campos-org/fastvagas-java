# Fast Vagas

This is a service that can help you find jobs, based on terms of search, it'll bring to you everything that matches.

# Getting Started

Unfortunately, this app is not hosted yet.

# Developer corner

## Requirements

- Java OpenJDK 11 (I recommend [sdkman](https://sdkman.io/install))
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
