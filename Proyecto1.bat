@echo off
setlocal

:: Verificar si Proyecto1.jar está en el directorio actual o en dist
if exist "Proyecto1.jar" (
    set JAR_PATH=Proyecto1.jar
) else if exist "dist\Proyecto1.jar" (
    set JAR_PATH=dist\Proyecto1.jar
) else (
    echo Proyecto1.jar no encontrado ni en el directorio actual ni en el subdirectorio dist.
    endlocal
    exit /b 1
)

:: Ejecutar el JAR en una nueva ventana y cerrar la ventana de cmd
start javaw -jar %JAR_PATH%

endlocal
exit