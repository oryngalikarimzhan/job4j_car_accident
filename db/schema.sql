create table accident_type (
    id serial primary key,
    name varchar(50)
);

create table rule (
    id serial primary key,
    name varchar(50)
);

create table accident (
    id serial primary key,
    name varchar(100),
    text varchar(2000),
    address varchar(200),
    type_id int references accident_type(id)
);

create table accident_rules (
    accident_id int references accident(id),
    rule_id int references rule(id)
);

insert into accident_type(name) values ('Две машины');
insert into accident_type(name) values ('Машина и человек');
insert into accident_type(name) values ('Машина и велосипед');

insert into rule(name) values ('Статья 1');
insert into rule(name) values ('Статья 2');
insert into rule(name) values ('Статья 3');

CREATE TABLE authorities (
                             id serial primary key,
                             authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users (
                       id serial primary key,
                       username VARCHAR(50) NOT NULL unique,
                       password VARCHAR(100) NOT NULL,
                       enabled boolean default true,
                       authority_id int not null references authorities(id)
);

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
        (select id from authorities where authority = 'ROLE_ADMIN'));