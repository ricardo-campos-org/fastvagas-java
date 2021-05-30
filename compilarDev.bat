@ECHO OFF

cd src\main\resources\static
cmd /C "npm install"
cmd /C "gulp"

@IF %ERRORLEVEL% NEQ 0 (
  cd ..\..\..\..\
  echo Erro ao executar Gulp. Erro: %ERRORLEVEL%
  pause
  Exit /B 1
)

cd ..\..\..\..\

cmd /C "mvn -T 4 -Pdev clean package install -DskipTests=true -DfailIfNoTests=false"

Exit /B %ERRORLEVEL%