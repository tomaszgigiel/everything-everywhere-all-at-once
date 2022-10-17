#!/bin/bash
DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; lein do clean, uberjar; java -cp target/uberjar/everything-everywhere-all-at-once-lein-uberjar.jar pl.tomaszgigiel.everythingeverywhereallatonce.core src/test/resources/custom.edn;cd -) #
