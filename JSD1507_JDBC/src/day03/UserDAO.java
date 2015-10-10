package day03;
/**
 * IUserDAO实现类
 * 对应数据库的操作
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import day01.DBUtil;

public class UserDAO implements IUserDAO{
   private static final String FIND_BY_ID="select * from user_sdw where id=?";
   private static final String INSERT="insert into user_sdw values(user_id_seq.nextval,?,?)";
   private static final String SELECT="select * from user_sdw";
   private static final String UPDATE="update user_sdw set password=? where username=?";
   private static final String DELETE="delete user_sdw where username=?";

	public static void main(String[] args) {

	}
   /**
    * 根据Id查询数据的SQL
    */
	@Override
	public User findById(int id) throws SQLException {
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(FIND_BY_ID);
			ps.setInt(1, id);
			User user=null;
		    rs =ps.executeQuery();
			if(rs.next()){
				user=new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
			}
			conn.close();
			ps.close();
			rs.close();
			return user;
		} catch (Exception e) {
			throw new SQLException(e);
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
			if(rs!=null){
				rs.close();
			}
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}
		
	}
/*
 * 
 * @see day03.IUserDAO#findAll()
 */
	@Override
	public List<User> findAll() throws SQLException {
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(SELECT);
			 rs=ps.executeQuery();
			 while(rs.next()){
				 System.out.print(rs.getInt("id")+":");
				 System.out.print(rs.getString("username")+":");
				 System.out.println(rs.getString("password"));
				 
			 }
		} catch (Exception e) {
			throw new SQLException(e);
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
			if(rs!=null){
				rs.close();
			}
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}
		return null;
	}

	@Override
	public User save(User user) throws SQLException {
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(INSERT,new String[]{"id"});
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			int flag=ps.executeUpdate();
			if(flag>0){
				rs=ps.getGeneratedKeys();
				rs.next();
				rs.getInt(1);
               		
                }
          return user;
		} catch (Exception e) {
			throw new SQLException(e);
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
			if(rs!=null){
				rs.close();
			}
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}
	}

	@Override
	public User update(User user) throws SQLException {
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		int flag=-1;
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(UPDATE);
			ps.setString(1,user.getUsername());
			ps.setString(2,user.getPassword());
			 flag=ps.executeUpdate();
			if(flag>0){
				rs=ps.getGeneratedKeys();
				rs.next();
				rs.getInt(1);
               System.out.println("更改成功");		
                }

		} catch (Exception e) {
			throw new SQLException(e);
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
			if(rs!=null){
				rs.close();
			}
			
		}
		return null;
	}

	@Override
	public boolean delete(User user) throws SQLException {
		Connection conn=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		boolean flag=false;
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement(DELETE);
			ps.setString(1,user.getUsername());
			 flag=ps.execute();
			if(flag){
				System.out.println("删除失败");
                }else{
                	System.out.println("删除成功");
                }
      
		} catch (Exception e) {
			throw new SQLException(e);
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
			if(rs!=null){
				rs.close();
			}
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}
		return false;
	}

}
