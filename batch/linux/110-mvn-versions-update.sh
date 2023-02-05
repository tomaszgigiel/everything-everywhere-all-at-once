#!/bin/bash
DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; mvn versions:display-dependency-updates; cd -) #
(cd $DIR_PROJECT; mvn versions:display-plugin-updates; cd -) #
(cd $DIR_PROJECT; mvn versions:use-latest-versions; cd -) #
(cd $DIR_PROJECT; mvn versions:update-properties; cd -) #
