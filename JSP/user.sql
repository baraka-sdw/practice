create table t_admin (
adminname varchar2(30),
password varchar2(30)
)

insert into t_admin values('张三','123456');
insert into t_admin values('李四','12345');

select * from t_admin

drop table t_user