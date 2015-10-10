package test;

import util.DAOFactory;
import dao.ComputerDAO;
import entity.Computer;

public class Test {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ComputerDAO dao = 
			(ComputerDAO) DAOFactory.getInstance(ComputerDAO.class);
		Computer c1 = new Computer("x200","������","x200.jpg",10000);
		dao.save(c1);
		Computer c2 = new Computer("x500","������","x500.jpg",20000);
		dao.save(c2);
		
		Computer cc = dao.findById(1);
		System.out.println(cc);
		
		for(Computer c : dao.findAll()){
			System.out.println(c);
		}
	}

}
