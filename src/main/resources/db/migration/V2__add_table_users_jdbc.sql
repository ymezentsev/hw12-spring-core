create table if not exists users_jdbc (
id bigint primary key auto_increment,
username varchar(50),
password varchar(100),
role varchar(50),
enabled int
);