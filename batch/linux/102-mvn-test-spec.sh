#!/bin/bash
DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; mvn -U clean '-Dtest=pl.tomaszgigiel.everythingeverywhereallatonce.*Tests' test; cd -) #
