\c shamkinroman1;

---  1. get all products which include TYPE CHEESE
select * from product as pro
    where pro.type_id=(select id from type where name='cheese');

-- 2. get all products which name consist of ICE-CREAM
select * from product as pro
    where pro.name like '%ice-cream%';

-- 3. get all products which expired_date ending in next mounth
select * from product as pro
    where pro.expired_date<'2018-02-23 04:11:44';

-- 4.  Print expensive product

select pro.name, pro.price from product as pro
    where pro.price=(select max(price) from product);


-- 5. Print quantity products certain TYPE
select  count(pro.id) from product as pro
    where pro.type_id=(select id from type where name='milk');

select  count(pro.id) from product as pro
    where pro.type_id=(select id from type where name='cheese');

select  count(pro.id) from product as pro
    where pro.type_id=(select id from type where name='water');

-- 6. Print all product types CHEESE and MILK.
select * from product as pro
    where pro.type_id=(select id from type where name='cheese') or pro.type_id=(select id from type where name='milk') ;

-- 7. Print all products which quantity less 10.
   select pro.type_id, count(*) from product as pro
    group by pro.type_id having count(*)<10;

-- 8. Print all products and their type.
select pro.name, ty.name from product as pro inner join type as ty on pro.type_id=ty.id;