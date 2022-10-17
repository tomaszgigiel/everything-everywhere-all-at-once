#!/bin/bash
DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; mvn -U clean package; java -cp target/everything-everywhere-all-at-once-jar-with-dependencies.jar pl.tomaszgigiel.everythingeverywhereallatonce.SimpleJavaApp First Second;cd -) #
