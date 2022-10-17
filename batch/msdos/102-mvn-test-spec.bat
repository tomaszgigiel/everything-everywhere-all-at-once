pushd %~dp0..\..\
call mvn -U clean test "-Dtest=pl.tomaszgigiel.everythingeverywhereallatonce.*Tests"
pause
popd
