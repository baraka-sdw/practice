create table t_login(
id number(10) primary key,
username varchar2(50) unique,
password varchar2(50),
sex varchar2(1)
);
create sequence login_id_seq increment by 1 start with 1
