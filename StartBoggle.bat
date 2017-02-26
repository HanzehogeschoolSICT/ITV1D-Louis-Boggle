@ECHO OFF

REM Enable UTF-8 in Windows CMD.
REM Without this words containing special characters will be displayed incorrectly.
CHCP 65001

ECHO.
ECHO Usage:
ECHO StartBoggle [--words C:\words.txt]
ECHO.

REM Launch Boggle if it has been built, passing all arguments.
IF EXIST out\artifacts\Boggle\Boggle.jar (
    CALL java -jar out\artifacts\Boggle\Boggle.jar %*
) else (
    ECHO Please build the JAR first.
)
