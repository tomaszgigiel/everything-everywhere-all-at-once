insert into table_with_blob(name, contents) values('sample-01.txt', FILE_READ('classpath:/pl.tomaszgigiel.utils.BlobUtils/blobs/sample-01.txt'));
insert into table_with_blob(name, contents) values('sample-02.txt', FILE_READ('classpath:/pl.tomaszgigiel.utils.BlobUtils/blobs/sample-02.txt'));
insert into table_with_blob(name, contents) values('sample-03.txt', FILE_READ('classpath:/pl.tomaszgigiel.utils.BlobUtils/blobs/sample-03.txt'));

insert into table_with_blob(name, contents) values('sample-11.txt', STRINGTOUTF8('Stillness is the key.'));
insert into table_with_blob(name, contents) values('sample-12.txt', STRINGTOUTF8('Ego is the enemy.'));
insert into table_with_blob(name, contents) values('sample-13.txt', STRINGTOUTF8('The obstacle is the way.'));
