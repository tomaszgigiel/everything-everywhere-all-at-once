pushd %~dp0..\..\
call mvn -U clean package
java -cp target/everything-everywhere-all-at-once-jar-with-dependencies.jar pl.tomaszgigiel.everythingeverywhereallatonce.SimpleJavaApp First Second
pause
popd
