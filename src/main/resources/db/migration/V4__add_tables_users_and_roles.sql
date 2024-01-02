create table if not exists roles (
id bigint primary key auto_increment,
role varchar(20)
);

create table if not exists users (
id bigint primary key auto_increment,
username varchar(50),
password varchar(100),
last_updated_date DATE NOT NULL,
created_date DATE NOT NULL
);

CREATE TABLE if not exists users_roles(
id bigint primary key auto_increment,
user_id bigint NOT NULL,
role_id bigint NOT NULL,
FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);


INSERT INTO roles (role) VALUES ('ADMIN'), ('USER');

INSERT INTO users (username, password, last_updated_date, created_date)
VALUES ('admin', '{noop}admin', NOW(), NOW()),
('user', '{noop}user', NOW(), NOW());

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1), (2, 2);