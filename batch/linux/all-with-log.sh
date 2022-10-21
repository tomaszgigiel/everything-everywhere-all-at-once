DIR_PROJECT="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"/../.. #
(cd $DIR_PROJECT; script -c "./batch/linux/all.sh" all-with-log.linux.log; cd -) #
(cd $DIR_PROJECT; ./batch/linux/006-sed-smooth-log-after.sh; cd -) #
