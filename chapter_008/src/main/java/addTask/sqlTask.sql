
drop database if exists shamadd;

create database shamadd;

\c shamadd;
CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company (id, name) values (1, 'Electric');
insert into company (id, name) values (2, 'Water');
insert into company (id, name) values (3, 'Earth');
insert into company (id, name) values (4, 'Sky');

insert into person (id, name, company_id) values (1, 'Anna' , 1);
insert into person (id, name, company_id) values (2, 'Boris' , 2);
insert into person (id, name, company_id) values (3, 'Vladimir' , 2);
insert into person (id, name, company_id) values (4, 'Georgy' , 3);
insert into person (id, name, company_id) values (5, 'Dmitry' , 3);
insert into person (id, name, company_id) values (6, 'Egor' , 3);
insert into person (id, name, company_id) values (7, 'Zina' , 3);
insert into person (id, name, company_id) values (8, 'Igor' , 4);


--// 1) Retrieve in a single query:
--// - names of all persons that are NOT in the company with id = 2  ------>>> // I change to 2
--// - company name for each person

select per.name, com.name as company from person as per inner join company as com on per.company_id=com.id and com.id!=2;



--// 2) Select the name of the company with the maximum number of persons + number of persons in this company

--first step
--Create table with company and numbers of worker
select c.name, count(*) from person as p inner join company as c on c.id=p.company_id group by c.name;


--second step
--formating out to degree

select c.name, count(*) from person as p inner join company as c on c.id=p.company_id group by c.name order by count(*) desc;

--three step
--select hight string with maximux worker  (add command LIMIT 1)
select c.name, count(company_id) from person as p inner join company as c on c.id=p.company_id group by c.name order by count(company_id) desc limit 1;

--four step
--adding two command to one in operator HAVING

select c.name, count(company_id) from person as p inner join company as c on c.id=p.company_id group by c.name having count(company_id)=(
select count(company_id) from person as p inner join company as c on c.id=p.company_id group by c.name order by count(company_id) desc limit 1);


--------------------------------------------------------------------------
--for example print company with low number worker (change DESC to ASC )--
--------------------------------------------------------------------------

select c.name, count(company_id) from person as p inner join company as c on c.id=p.company_id group by c.name having count(company_id)=(
select count(company_id) from person as p inner join company as c on c.id=p.company_id group by c.name order by count(company_id) asc limit 1);
