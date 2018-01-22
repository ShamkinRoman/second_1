drop database if exists shamkinroman1;

create database shamkinroman1;
\c shamkinroman1;

----------------------
--create table TYPE--
---------------------
create table type(
    id serial primary key,
    name character varying (300)
);


------------------------
--create table product--
------------------------
create table product (
    id serial primary key,
	name character varying (300),
	type_id integer references type(id),
	expired_date timestamp,
	price float
	);


--fill table TYPE
insert into type(name ) values ('milk');
insert into type(name ) values ('cheese');
insert into type(name ) values ('water');

--fill table PRODUCT
insert into product( name, type_id, expired_date, price) values ( 'milk 2,5%', 1, '2018-01-28 04:11:44', 2.20);
insert into product( name, type_id, expired_date, price) values ( 'cheese Russia', 2, '2018-02-08 04:11:44', 3.30);
insert into product( name, type_id, expired_date, price) values ( 'water Aqua', 3, '2018-01-03 04:11:44', 4.40);
insert into product( name, type_id, expired_date, price) values ( 'cheese-butter', 2, '2018-04-08 04:11:44', 5.50);
insert into product( name, type_id, expired_date, price) values ( 'ice-cream ESKIMO', 1, '2018-04-08 04:11:44', 6.60);
insert into product( name, type_id, expired_date, price) values ( 'milk 3,2%', 1, '2018-05-08 04:11:44', 7.70);
insert into product( name, type_id, expired_date, price) values ( 'water Amur', 3, '2018-06-08 04:11:44', 9.90);
insert into product( name, type_id, expired_date, price) values ( 'cheese ROKFOR', 2, '2018-07-08 04:11:44', 9.90);


insert into product( name, type_id, expired_date, price) values ( 'water Amur2', 3, '2018-09-08 04:11:44', 8.80);
insert into product( name, type_id, expired_date, price) values ( 'water Amur3', 3, '2018-09-08 04:11:44', 8.80);
insert into product( name, type_id, expired_date, price) values ( 'water Amur4', 3, '2018-09-08 04:11:44', 8.80);
insert into product( name, type_id, expired_date, price) values ( 'water Amur5', 3, '2018-09-08 04:11:44', 8.80);
insert into product( name, type_id, expired_date, price) values ( 'water Amur6', 3, '2018-09-08 04:11:44', 8.80);
insert into product( name, type_id, expired_date, price) values ( 'water Amur7', 3, '2018-09-08 04:11:44', 8.80);
insert into product( name, type_id, expired_date, price) values ( 'water Amur8', 3, '2018-09-08 04:11:44', 8.80);
insert into product( name, type_id, expired_date, price) values ( 'water Amur9', 3, '2018-09-08 04:11:44', 8.80);
insert into product( name, type_id, expired_date, price) values ( 'water Amur10', 3, '2018-09-08 04:11:44', 8.80);
insert into product( name, type_id, expired_date, price) values ( 'water Amur11', 3, '2018-09-08 04:11:44', 8.80);
