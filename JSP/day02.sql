create table t_emp(
id number(4) primary key,
name varchar(20),
salary number(7,2),
age number(3)
);

create sequence emp_id_seq start with 1 increment by 1;

insert into t_emp values(emp_id_seq.nextval,'张三',3000.00,20);
insert into t_emp values(emp_id_seq.nextval,'李四',3000.00,20);
insert into t_emp values(emp_id_seq.nextval,'王五',3000.00,20);
insert into t_emp values(emp_id_seq.nextval,'赵六',3000.00,20);
select * from t_emp

delete t_emp where id>=1

drop sequence emp_id_seq
