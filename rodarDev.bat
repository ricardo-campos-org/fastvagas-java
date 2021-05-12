@ECHO OFF

java -Dspring.profiles.active=dev -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787 -jar target/dev-*.jar