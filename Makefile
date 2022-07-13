SHELL = /usr/bin/env bash -o pipefail
.SHELLFLAGS = -ec

env ?=
MAVEN ?= ~/.sdkman/candidates/maven/current/bin/mvn
JAVA ?= ~/.sdkman/candidates/java/current/bin/java

export DATABASE_URL ?= jdbc:postgresql://fast-database:5432/fastservice
export DATABASE_USERNAME ?= postgres
export DATABASE_PASSWORD ?= pwd

## Local development
.PHONY: clean
clean: # maven
	$(MAVEN) clean

.PHONY: build
build: # Build
ifeq ($(env),)
	$(MAVEN) -Pdev clean package install -DskipTests=true -DfailIfNoTests=false
else ifeq ($(env),dev)
	$(MAVEN) -Pdev clean package install -DskipTests=true -DfailIfNoTests=false
else ifeq ($(env),prod)
	$(MAVEN) -Pprod clean package install -DskipTests=true -DfailIfNoTests=false
endif

#.PHONY: init-db
#init-db: ## Usage: make init-db DATABASE_PASSWORD=<password>
#	DATABASE_URL=$(DATABASE_URL) \
#	DATABASE_USERNAME=$(DATABASE_USERNAME) \
#	DATABASE_PASSWORD=$(DATABASE_PASSWORD) \
#	./init-db.sh

.PHONY: docker-run-deps
docker-run-deps: ## Run Docker dependencies. Usage: make docker-run-deps
	docker-compose -p "fastservice" -f ./docker-compose-deps.yml up

.PHONY: docker-teardown-deps
docker-teardown-deps: ## Delete Docker dependencies. Usage: make docker-down-deps
	docker-compose -p "fastservice" -f ./docker-compose-deps.yml down --remove-orphans

.PHONY: run
run: # maven ## Local development with PostgreSQL in Docker (requires make docker-run-deps). Usage: make
ifeq ($(env),)
	SPRING_DATASOURCE_PASSWORD=$(DATABASE_PASSWORD) \
    $(MAVEN) spring-boot:run \
    -Dspring-boot.run.profiles=dev \
    -Drun.jvmArguments="-Dfile.encoding=UTF-8 -Xdebug \
      -Xrunjdwp:transport=dt_socket,server=y,address=8787"
else ifeq ($(env),dev)
	SPRING_DATASOURCE_PASSWORD=$(DATABASE_PASSWORD) \
    $(JAVA) -Dspring.profiles.active=dev \
    	-Dfile.encoding=UTF-8 \
    	-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787 \
    	-jar target/dev-1.0.jar
else ifeq ($(env),prod)
	SPRING_DATASOURCE_PASSWORD=$(DATABASE_PASSWORD) \
    $(JAVA) -Dspring.profiles.active=prod \
    -Dfile.encoding=UTF-8 \
    -jar target/prod-1.0.jar
endif


