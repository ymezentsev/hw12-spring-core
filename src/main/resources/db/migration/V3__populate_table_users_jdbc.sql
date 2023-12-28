insert into users_jdbc (username, password, role, enabled)
values ('user', '{noop}jdbcDefault', 'USER', 1),
('admin', '{bcrypt}$2a$10$t7OsV17px64y1vIV1HTqTuBWA82FpLzuMJ3CYKEkdQZiikKb8y2Ra', 'ADMIN', 1);

-- password for admin - admin