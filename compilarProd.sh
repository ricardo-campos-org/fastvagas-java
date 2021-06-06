#!/bin/bash

cd src/main/resources/static
npm install
gulp --production
if [ "$?" -ne 0 ]; then
    cd ../../../../
    echo "Erro ao executar Gulp."
    exit 1
fi
cd ../../../../
mvn -Pprod clean package install -DskipTests=true -DfailIfNoTests=false