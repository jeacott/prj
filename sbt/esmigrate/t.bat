@echo off
setlocal
del /Q %~dp0*.log
:: test
call sbt.bat "testOnly *TestSimple"
endlocal
