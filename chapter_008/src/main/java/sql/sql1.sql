
drop database if exists shamkinroman;

create database shamkinroman;
\c shamkinroman;


----------------------
--create table USER.--
----------------------
create table users (
	id serial primary key,
	surname character varying (300),
	name character varying (300)
	);
----------------------------------


----------------------
--create table ROLE.--
----------------------
create table roles (
	id serial primary key,
	roles character varying (400) unique
	);
--------------------------

----------------------
--create table RULE.--
----------------------
create table rules(
	id serial primary key,
	char_roles character varying (400) references roles(roles),
	prava character varying (400)
	);
---------------------------------


----------------------
--create table ITEM.--
----------------------
create table item (
	id serial primary key,
	id_users integer references users(id),
	description_item text
	);


--------------------------
--create table COMMENTS.--
--------------------------
create table commets (
	id serial primary key,
	id_item integer references item(id),
	description_coment text
	);

------------------------
--create table ATTACH.--
------------------------
create table attachs (
    id serial primary key,
    id_item integer references item(id),
    files text
    );

--------------------------
--create table CATEGORY.--
--------------------------
create table category (
    id serial primary key,
    id_item integer references item(id),
    condition boolean--True открыта, False закрыта.
    );

--fill table users
insert into users(surname, name) values ('Shamkin','Roman');
insert into users(surname, name) values ('Petrov','Nikolay');
insert into users(surname, name) values ('Volkov','Alexander');

--fill table roles
insert into roles(roles) values ('Administrator');
insert into roles(roles) values ('User');
insert into roles(roles) values ('Guest');

--fill table rules
insert into rules(char_roles, prava) values ('Administrator', 'Delete');
insert into rules(char_roles, prava) values ('Administrator', 'Record');
insert into rules(char_roles, prava) values ('Administrator', 'Read');
insert into rules(char_roles, prava) values ('User', 'Recors');
insert into rules(char_roles, prava) values ('User', 'Read');
insert into rules(char_roles, prava) values ('Guest', 'Read');

--fill table item
insert into item(id_users, description_item) values (1, 'Item #1');
insert into item(id_users, description_item) values (2, 'Item #2');
insert into item(id_users, description_item) values (3, 'Item #3');
insert into item(id_users, description_item) values (1, 'Item #4');

--fill table commets
insert into commets(id_item, description_coment) values (1, 'Text for item #1');
insert into commets(id_item, description_coment) values (2, 'Text for item #2');
insert into commets(id_item, description_coment) values (3, 'Text for item  #3');
insert into commets(id_item, description_coment) values (4, 'Text for item #4');
insert into commets(id_item, description_coment) values (4, 'Text for item #4 continue 1');
insert into commets(id_item, description_coment) values (4, 'Text for item #4 continue 2');

--fill table attachs
insert into attachs(id_item, files) values (1, 'Here file for item #1');
insert into attachs(id_item, files) values (1, 'More file for item  #1');
insert into attachs(id_item, files) values (2, 'Here file for item  #2');


--fill table category
insert into category(id_item, condition) values (1, FALSE);
insert into category(id_item, condition) values (2, TRUE);
insert into category(id_item, condition) values (3, FALSE);
insert into category(id_item, condition) values (4, TRUE);

--print filling tables
select  * from users;
select  * from roles;
select  * from rules;
select  * from item;

select * from item left join users on (users.id=item.id_users);

select * from commets left join item on (item.id=commets.id_item);

select * from attachs left join item on (item.id=attachs.id_item);

select * from category left join item on (item.id=category.id_item);

select * from category left join item on (item.id=category.id_item) left join users on (users.id=item.id_users);
