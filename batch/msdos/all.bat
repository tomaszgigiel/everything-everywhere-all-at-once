pushd %~dp0
call 101-mvn-compile.bat
call 102-mvn-test-spec.bat
call 103-mvn-test.bat
call 104-mvn-exec.bat
call 105-mvn-exec-specific.bat
call 106-mvn-java-cp.bat
call 107-mvn-java-jar.bat
call 108-mvn-uberjar.bat
REM 109-mvn-jshell.bat
call 110-mvn-install.bat

pause
