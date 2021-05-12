@ECHO OFF

mvn -Pdev clean package install -DskipTests=true -DfailIfNoTests=false