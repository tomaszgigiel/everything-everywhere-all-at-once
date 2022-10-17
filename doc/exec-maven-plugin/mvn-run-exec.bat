pushd %~dp0..\..\
call mvn -f pom-exec.xml clean package exec:java
pause
popd
