pushd %~dp0..\..\
call mvn -U clean test
pause
popd
