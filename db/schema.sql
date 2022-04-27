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