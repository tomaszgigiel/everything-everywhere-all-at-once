DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
# exit
(cd $DIR_PROJECT; lein repl; cd -) #
