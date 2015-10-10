package spring;

import java.sql.Connection;
import java.util.Calendar;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		// Foo f1=(Foo) context.getBean("f1");
		// Foo f2=(Foo) context.getBean("f2");
		// System.out.println(f1);
		// System.out.println(f2);
		// Calendar c=(Calendar) context.getBean("c");
		// System.out.println(c);
		// Bar bar=(Bar) context.getBean("b");
		// System.out.println(bar.hashCode());
		// Bar bar1=(Bar) context.getBean("b");
		// System.out.println(bar1.hashCode());
		// Goo g=(Goo) context.getBean("g");
		// System.out.println(g);
		// EmpService es=(EmpService) context.getBean("es");
		// EmpService es2=(EmpService) context.getBean("empService");
		// System.out.println(es2.getEmpDao());
		// DBUtil bds=(DBUtil) context.getBean("dateBase2");
		// bds.getConnection();
		// System.out.println(es.getEmpDao());
		// System.out.println(es.getDeptDao());
		Bar b = (Bar) context.getBean("vector");
		System.out.println(b.getList().toString());
		System.out.println(b.getSet().toString());
		System.out.println(b.getProps().toString());
		System.out.println(b.getMap().toString());
	}
}
