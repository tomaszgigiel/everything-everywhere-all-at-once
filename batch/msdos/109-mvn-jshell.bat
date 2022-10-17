pushd %~dp0..\..\
REM https://stackoverflow.com/questions/47705036/in-jshell-how-to-import-classpath-from-a-maven-project
call mvn -U clean package dependency:build-classpath -DincludeTypes=jar -Dmdep.outputFile=cp.txt
REM /exit
for /F %%i in (cp.txt) do jshell --class-path "%%i;target/classes"
del /f cp.txt
pause
popd
