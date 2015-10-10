package test;

import util.DAOFactory;
import dao.UserDAO;
import entity.User;

public class Test {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		UserDAO dao = (UserDAO) DAOFactory.getInstance(UserDAO.class);
		User user = new User();
		user.setUsername("zs");
		user.setPassword("test");
		user.setSex("m");
		// dao.save(user);
		User user2 = dao.findByUsername("zs");
		System.out.println(user2);
	}

}
