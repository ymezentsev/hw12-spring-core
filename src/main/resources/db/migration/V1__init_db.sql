create table if not exists note (
id bigint primary key auto_increment,
title varchar(255),
content varchar(1000)
);