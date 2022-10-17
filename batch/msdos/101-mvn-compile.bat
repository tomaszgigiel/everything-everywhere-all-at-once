pushd %~dp0..\..\
call mvn -U clean compile
pause
popd
