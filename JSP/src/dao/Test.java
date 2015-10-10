package dao;

import java.util.List;

import entity.Employee;

public class Test {

	public static void main(String[] args) throws Exception{
		 List<Employee> emps =new EmployeeDAO().findAll();
		 for(Object o:emps){
			 System.out.println(o);
		 }
	}

}
