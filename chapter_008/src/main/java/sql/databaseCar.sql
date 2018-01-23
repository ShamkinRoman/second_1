drop database if exists shamkinromancar;

create database shamkinromancar;
\c shamkinromancar;
--create table body
create table body (
    id serial primary key,
    name varchar(200)
);

--create table engine
create table engine(
    id serial primary key,
    name varchar(200)
);

--create table kpp
create table kpp (
    id serial primary key,
    name varchar(200)
);

--create table car
create table car(
    id serial primary key,
    name varchar(200),
    body_id integer references body(id),
    engine_id integer references engine(id),
    kpp_id integer references kpp(id)
);

--fill tables body, engine, kpp, car

insert into body (name) values ('sedan');
insert into body (name) values ('hachback');

insert into engine(name) values ('V-1 111 cm');
insert into engine(name) values ('V-2 222 cm');
insert into engine(name) values ('V-3 333 cm');

insert into kpp (name) values ('automatic');
insert into kpp (name) values ('mechanical');

insert into car(name, body_id, engine_id, kpp_id) values ('TOYOTA-ONE', 2, 3, 1);

--Print all car
select * from car;

--Print unused body
select b.name from body as b left outer join car as c on c.body_id=b.id where c.id is null;

--Print unused engine
select e.name from engine as e left outer join car as c on c.engine_id=e.id where c.id is null;

--Print unused kpp
select k.name from kpp as k left outer join car as c on c.kpp_id=k.id where c.id is null;