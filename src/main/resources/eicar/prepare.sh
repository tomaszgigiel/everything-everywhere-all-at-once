#!/bin/bash
# https://www.eicar.org/download-anti-malware-testfile/

cd /home/tomasz/Documents/workspace/everything-everywhere-all-at-once/src/main/resources/eicar

rm -rf eicar_wget
wget https://secure.eicar.org/eicar.com -P ./eicar_wget/
wget https://secure.eicar.org/eicar.com.txt -P ./eicar_wget/
wget https://secure.eicar.org/eicar_com.zip -P ./eicar_wget/
wget https://secure.eicar.org/eicarcom2.zip -P ./eicar_wget/

rm -rf eicar_splitted
mkdir -p eicar_splitted

mkdir -p ./eicar_splitted/eicar_com
split --bytes=50 ./eicar_wget/eicar.com ./eicar_splitted/eicar_com/eicar.com

mkdir -p ./eicar_splitted/eicar_com_txt
split --bytes=50 ./eicar_wget/eicar.com.txt ./eicar_splitted/eicar_com_txt/eicar.com.txt

mkdir -p ./eicar_splitted/eicar_com_zip
split --bytes=100 ./eicar_wget/eicar_com.zip ./eicar_splitted/eicar_com_zip/eicar_com.zip

mkdir -p ./eicar_splitted/eicarcom2_zip
split --bytes=200 ./eicar_wget/eicarcom2.zip ./eicar_splitted/eicarcom2_zip/eicarcom2.zip
