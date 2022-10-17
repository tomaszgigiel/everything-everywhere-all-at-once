pushd %~dp0..\..\
call mvn -U clean package
pause
popd
