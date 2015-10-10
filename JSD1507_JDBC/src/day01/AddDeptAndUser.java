package day01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * 添加一个部门，同时为其添加几个用户
 * @author Administrator
 *
 */
public class AddDeptAndUser {
	public static void main(String[] args) throws Exception {
		Connection conn=DBUtil.getConnection();
		conn.setAutoCommit(false);
		try {
			 
			
			//1向部门表中插入数据 department_deptno_seq.nextval,'IT','北京'
			String sql="insert into department(deptno ,dname,loc) values(department_deptno_seq.nextval,?,?)";
			/**connection重载的preparstatement方法
			 * 第一个参数为预编译sql
			 * 第二个参数是一个字符串数组，用于指明
			 * 当使用该sql插入一条数据后，该条数据的那些字段的值需要获取
			 */
		
			PreparedStatement stmt =conn.prepareStatement(sql,new String[]{"deptno"} );
			stmt.setString(1,"IT" );
			stmt.setString(2, "北京");
			stmt.addBatch();
			stmt.executeBatch();
			//使用序列的值作为主键向部门表插入信息
			//执行插入操作
			/*
			 * 该方法的作用是获取刚刚插入的数据中我们关心的字段的值
			 */
			 ResultSet rs=stmt.getGeneratedKeys();
			 ResultSetMetaData rsm=rs.getMetaData();
			 System.out.println(rsm.getColumnName(1));
			
			rs.next();
			int id=rs.getInt(1);
			/*
			 * 1:resultset.getInt(int index)
			 * resultset.getInt(String name)
			 */
			System.out.println("生成的主键值是"+id);
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();

		}finally{
			conn.setAutoCommit(true);
		}
	}

}
