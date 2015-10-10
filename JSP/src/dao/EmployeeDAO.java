package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.mail.iap.Response;

import entity.Employee;
import entity.User;

//员工信息的操作类（增删改查）
public class EmployeeDAO {
	/*
	 * 增加員工信息
	 */
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	public void add(Employee emp) throws Exception {

		try {
			conn = DBUtil.getConnection();
			ps = conn
					.prepareStatement("insert into t_emp values(emp_id_seq.nextval,?,?,?)");
			ps.setString(1, emp.getName());
			ps.setFloat(2, emp.getSalary());
			ps.setInt(3, emp.getAge());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("添加失败");
			throw e;
		} finally {
			DBUtil.close(conn);
		}
	}

	/**
	 * 查询员工信息
	 * 
	 * @throws Exception
	 */
	public List<Employee> findAll() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		List<Employee> emps = new ArrayList<Employee>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from t_emp");
			rs = ps.executeQuery();
			while (rs.next()) {
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setSalary(rs.getFloat("salary"));
				emp.setAge(rs.getInt("age"));
				emps.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(conn);
		}
		return emps;
	}

	public void delete(Employee emp) throws Exception {
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("delete t_emp where id=?");
			ps.setInt(1, emp.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(conn);
		}
	}

	public void modify(Employee emp) throws Exception {
		try {
			conn = DBUtil.getConnection();
			ps = conn
					.prepareStatement("update t_emp set name=?,salary=?,age=? where id=?");
			ps.setString(1, emp.getName());
			ps.setFloat(2, emp.getSalary());
			ps.setInt(3, emp.getAge());
			ps.setInt(4, emp.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtil.close(conn);
		}
	}
	public User findAllByName(String username) throws Exception{
		User user=null;
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement("select * from t_user where username=?");
			ps.setString(1, username);
			rs=ps.executeQuery();
			while(rs.next()){
			 user=new User();
//			 user.setId(rs.getInt("id"));
			 user.setUserName(rs.getString("username"));
			 user.setPwd(rs.getString("pwd"));
//			 user.setName(rs.getString("name"));
//			 user.setGender(rs.getString("gender"));
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return user;
	}
	public void addUser(User user) throws Exception {

		try {
			conn = DBUtil.getConnection();
			ps = conn
					.prepareStatement("insert into t_user values(user_id_seq.nextval,?,?,?,?)");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			ps.setString(3, user.getName());
			ps.setString(4,user.getGender());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("添加失败");
			throw e;
		} finally {
			DBUtil.close(conn);
		}
	}
}
