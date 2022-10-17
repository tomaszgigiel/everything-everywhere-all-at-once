pushd %~dp0..\..\
call mvn -U clean package exec:java -Dexec.mainClass="pl.tomaszgigiel.everythingeverywhereallatonce.SimpleJavaApp" -Dexec.args="First Second" -Dexec.cleanupDaemonThreads=false
pause
popd
