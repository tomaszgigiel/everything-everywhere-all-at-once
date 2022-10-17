#!/bin/bash
DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; sbt clean "runMain pl.tomaszgigiel.everythingeverywhereallatonce.SimpleScalaApp First Second"; cd -) #
