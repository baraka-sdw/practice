package day03;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * ҵ���߼���
 * @author Administrator
 *
 */
public class UserService {

	public static void main(String[] args) throws SQLException {
		Scanner sc=new Scanner(System.in);
//		System.out.println("������id");
//		int id=sc.nextInt();
//        IUserDAO dao=new UserDAO();
//        User user= dao.findById(id);
//        System.out.println("�û���"+user.getUsername());
//   
		
		
//		IUserDAO dao=new UserDAO();
//		User user=new User();
//		user.setUsername("����");
//		user.setPassword("123");
//		dao.save(user);
		
//		IUserDAO dao=new UserDAO();
//		dao.findAll();
		
		
//		System.out.println("�������û���");
//		String username=sc.nextLine();
//		System.out.println("�������޸ĵ�����");
//		String password=sc.nextLine();
//		IUserDAO dao=new UserDAO();
//		User user =new User();
//		user.setUsername(username);
//		user.setPassword(password);
//		dao.update(user);
		
		System.out.println("������Ҫɾ�����û���");
		String username =sc.nextLine();
		IUserDAO dao=new UserDAO();
		User user=new User();
		user.setUsername(username);
		dao.delete(user);
	}

}
