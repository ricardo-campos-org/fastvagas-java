#!/bin/bash

java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787 -jar target/webapp-*.jar