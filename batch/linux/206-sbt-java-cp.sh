#!/bin/bash
DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; sbt clean assembly; java -cp target/scala-3.2.0/everything-everywhere-all-at-once-uberjar.jar pl.tomaszgigiel.everythingeverywhereallatonce.SimpleScalaApp First Second;cd -) #
