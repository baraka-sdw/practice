create table t_user (
 id number(4) primary key,
 username varchar2(20) unique,
 pwd varchar2(20),
 name varchar2(50),
 gender char(20)
);

create sequence user_id_seq start with 1 increment 	by 1;;

select * from t_user;

drop table  t_user

delete t_user where id>25

update t_user set username='ÕÅ3' where id=25
select username,pwd from t_user
select * from t_user where username='Íõ»¨'