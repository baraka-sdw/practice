create table t_sale(
 id number(6) primary key,
 prodName varchar2(20),
 qty number(4)
);

create sequence sale_id_seq increment by 1 start with 1

select * from (select rownum r,a.* from(select * from t_sale order by qty  desc)a)c where c.r<4	