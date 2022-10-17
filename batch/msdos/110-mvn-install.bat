pushd %~dp0..\..\
call mvn -U clean package install
pause
popd
