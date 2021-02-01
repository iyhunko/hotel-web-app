insert into roles (id, name) values(1, 'ADMIN');

insert into users (id, email, firstname, lastname, password) values(1, 'admin@admin.com', 'Ivan', 'Hunko', '$2a$10$rolRcqgcQ5OpiiNOHAdQfuROS0kagXwTLlE.K.nab3WjLVLKRnCwK');

insert into users (id, email, firstname, lastname, password) values(2, 'user@user.com', 'Ivan', 'User', '$2a$10$rolRcqgcQ5OpiiNOHAdQfuROS0kagXwTLlE.K.nab3WjLVLKRnCwK');

insert into users_roles (user_id, role_id) values(1, 1);