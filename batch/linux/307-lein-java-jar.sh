#!/bin/bash
DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; lein do clean, uberjar; java -jar target/uberjar/everything-everywhere-all-at-once-lein-uberjar.jar src/test/resources/custom.edn;cd -) #
