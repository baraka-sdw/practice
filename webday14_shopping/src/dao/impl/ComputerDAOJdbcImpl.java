package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import dao.ComputerDAO;
import entity.Computer;
public class ComputerDAOJdbcImpl implements ComputerDAO {

	public List<Computer> findAll() throws Exception{
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		Statement stat = conn.createStatement();
		String sql = "select * from t_computer";
		ResultSet rst = stat.executeQuery(sql);
		List<Computer> computers = new 
		 ArrayList<Computer>();
		while(rst.next()){
			Computer c = new Computer();
			c.setId(rst.getLong("id"));
			c.setName(rst.getString("name"));
			c.setDesc(rst.getString("description"));
			c.setPrice(rst.getDouble("price"));
			c.setPic(rst.getString("pic"));
			computers.add(c);
		}
		DBUtil.close(conn);
		return computers;
	}

	public Computer findById(long id) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		Statement stat = conn.createStatement();
		String sql = "select * from t_computer where id=" + id;
		System.out.println(sql);
		ResultSet rst = stat.executeQuery(sql);
		Computer c = null;
		while(rst.next()){
			c = new Computer();
			c.setId(rst.getLong("id"));
			c.setName(rst.getString("name"));
			c.setDesc(rst.getString("description"));
			c.setPrice(rst.getDouble("price"));
			c.setPic(rst.getString("pic"));
		}
		DBUtil.close(conn);
		return c;
	}

	public void save(Computer c) throws Exception{
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		PreparedStatement prep = 
			conn.prepareStatement("insert into t_computer(name,description,pic,price) values(?,?,?,?)");
		prep.setString(1,c.getName());
		prep.setString(2,c.getDesc());
		prep.setString(3, c.getPic());
		prep.setDouble(4, c.getPrice());
		prep.executeUpdate();
		DBUtil.close(conn);
	}

}
