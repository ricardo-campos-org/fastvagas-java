@ECHO OFF

SET "JAVA_HOME=C:\Users\Ricardo\java-11-openjdk-11.0.10.9-1.windows.ojdkbuild.x86_64"
SET "JAVA=C:\Users\Ricardo\java-11-openjdk-11.0.10.9-1.windows.ojdkbuild.x86_64\bin\java.exe"
%JAVA% -Dspring.profiles.active=dev -Dfile.encoding=UTF-8 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8787 -jar target/dev-1.0.jar