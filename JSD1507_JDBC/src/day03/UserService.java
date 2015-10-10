package day03;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * 业务逻辑类
 * @author Administrator
 *
 */
public class UserService {

	public static void main(String[] args) throws SQLException {
		Scanner sc=new Scanner(System.in);
//		System.out.println("请输入id");
//		int id=sc.nextInt();
//        IUserDAO dao=new UserDAO();
//        User user= dao.findById(id);
//        System.out.println("用户名"+user.getUsername());
//   
		
		
//		IUserDAO dao=new UserDAO();
//		User user=new User();
//		user.setUsername("張三");
//		user.setPassword("123");
//		dao.save(user);
		
//		IUserDAO dao=new UserDAO();
//		dao.findAll();
		
		
//		System.out.println("请输入用户名");
//		String username=sc.nextLine();
//		System.out.println("请输入修改的密码");
//		String password=sc.nextLine();
//		IUserDAO dao=new UserDAO();
//		User user =new User();
//		user.setUsername(username);
//		user.setPassword(password);
//		dao.update(user);
		
		System.out.println("请输入要删除的用户名");
		String username =sc.nextLine();
		IUserDAO dao=new UserDAO();
		User user=new User();
		user.setUsername(username);
		dao.delete(user);
	}

}
