CALL compilar.bat
@IF %ERRORLEVEL% NEQ 0 (
    EXIT /B 1
)
call rodarDev.bat