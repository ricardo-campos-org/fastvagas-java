#!/bin/bash

./compilarDev.sh

if [ "$?" -ne 0 ]; then
    cd ../../../../
    echo "Erro ao compilar."
    exit 1
fi

java -Dspring.profiles.active=dev -Dfile.encoding=UTF-8 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787 -jar target/dev-1.0.jar