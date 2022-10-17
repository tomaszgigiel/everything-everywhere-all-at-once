pushd %~dp0..\..\
call mvn -U clean package exec:java -Dexec.cleanupDaemonThreads=false
pause
popd
