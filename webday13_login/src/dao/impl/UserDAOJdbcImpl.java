package dao.impl;

import dao.UserDAO;
import entity.User;
import java.sql.*;

import util.DBUtil;

public class UserDAOJdbcImpl implements UserDAO {

	public void save(User user) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		String sql = "insert into t_login(username,password,sex) values(?,?,?)";
		PreparedStatement prep = conn.prepareStatement(sql);
		prep.setString(1, user.getUsername());
		prep.setString(2, user.getPassword());
		prep.setString(3, user.getSex());
		prep.executeUpdate();
		DBUtil.close(conn);
	}

	public User findByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = DBUtil.getConnection();
		Statement stat = conn.createStatement();
		String sql = "select * from t_login where username='" + username + "'";
		ResultSet rst = stat.executeQuery(sql);
		User user = null;
		while (rst.next()) {
			user = new User();
			user.setUsername(rst.getString("username"));
			user.setPassword(rst.getString("password"));
			user.setSex(rst.getString("sex"));
			user.setId(rst.getLong("id"));
		}
		DBUtil.close(conn);
		return user;
	}

}
