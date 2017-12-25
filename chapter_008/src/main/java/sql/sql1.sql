
--��� ����� ���������, � ��������� ������ �������
-- chcp 1251

drop database if exists shamkinroman;

create database shamkinroman;
\c shamkinroman;

--�� �������� ��� �������, ��� ����� ������?
--drop table if exists users;
--drop table if exists roles;
--drop table if exists rules;
--drop table if exists item;
--drop table if exists commets;
--drop table if exists attach;
--drop table if exists category;


----------------------------------
--������� ������� �������������.--
----------------------------------
create table users (
	id serial primary key,
	surname character varying (300),
	name character varying (300)
	);
----------------------------------


--------------------------
--������� ������� �����.--
--------------------------
create table roles (
	id serial primary key,
	roles character varying (400) unique
	);
--------------------------

--------------------------------
--������� ������� ����� �����.--
--------------------------------
create table rules(
	id serial primary key,
	char_roles character varying (400) references roles(roles),
	prava character varying (400)
	);
---------------------------------


---------------------------
--������� ������� ������.--
---------------------------
create table item (
	id serial primary key,
	id_users integer references users(id),
	description_item text
	);


--------------------------------
--������� ������� �����������.--
--------------------------------
create table commets (
	id serial primary key,
	id_item integer references item(id),
	description_coment text
	);

-------------------------------------
--������� ������� ����� � ������.----
--(� ��� ��� �����, ����� � �� ����--
-------------------------------------
create table attachs (
    id serial primary key,
    id_item integer references item(id),
    files text
    );

-------------------------------------
--������� ������� ��������� ������---
-------------------------------------
create table category (
    id serial primary key,
    id_item integer references item(id),
    condition boolean--True �������, False �������.
    );

--��������� ������� users
insert into users(surname, name) values ('������','�����');
insert into users(surname, name) values ('������','�������');
insert into users(surname, name) values ('������','���������');

--��������� ������� roles
insert into roles(roles) values ('�������������');
insert into roles(roles) values ('������������');
insert into roles(roles) values ('�����');

--��������� ������� rules
insert into rules(char_roles, prava) values ('�������������', '��������');
insert into rules(char_roles, prava) values ('�������������', '������');
insert into rules(char_roles, prava) values ('�������������', '������');
insert into rules(char_roles, prava) values ('������������', '������');
insert into rules(char_roles, prava) values ('������������', '������');
insert into rules(char_roles, prava) values ('�����', '������');

--��������� ������� item
insert into item(id_users, description_item) values (1, '������ �1');
insert into item(id_users, description_item) values (2, '������ �2');
insert into item(id_users, description_item) values (3, '������ �3');
insert into item(id_users, description_item) values (1, '������  �4');

--��������� ������� commets
insert into commets(id_item, description_coment) values (1, '����� ������ �1');
insert into commets(id_item, description_coment) values (2, '����� ������ �2');
insert into commets(id_item, description_coment) values (3, '����� ������ �3');
insert into commets(id_item, description_coment) values (4, '����� ������ �4');
insert into commets(id_item, description_coment) values (4, '����� ������ �4_1');
insert into commets(id_item, description_coment) values (4, '����� ������ �4_2');

--��������� ������� attachs
insert into attachs(id_item, files) values (1, '������ ������ ���� ���� � ������  �1');
insert into attachs(id_item, files) values (1, '������ ������ ���� ��� ���� � ������  �1');
insert into attachs(id_item, files) values (2, '������ ������ ���� ���� � ������  �2');


--��������� ������� category
insert into category(id_item, condition) values (1, FALSE);
insert into category(id_item, condition) values (2, TRUE);
insert into category(id_item, condition) values (3, FALSE);
insert into category(id_item, condition) values (4, TRUE);

select  * from users;
select  * from roles;
select  * from rules;
select  * from item;

select * from item left join users on (users.id=item.id_users);

select * from commets left join item on (item.id=commets.id_item);

select * from attachs left join item on (item.id=attachs.id_item);

select * from category left join item on (item.id=category.id_item);

select * from category left join item on (item.id=category.id_item) left join users on (users.id=item.id_users);
