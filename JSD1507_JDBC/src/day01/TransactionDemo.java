package day01;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionDemo {
/*
 * 事务控制
 */
	public static void main(String[] args) throws Exception {
		Connection conn=DBUtil.getConnection();
		String sql="insert into user_sdw"+"(id,username,password)"+"values(?,?,?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		//自动提交关闭
		conn.setAutoCommit(false);
		try {
			for(int i=10;i<601;i++){
				ps.setInt(1, i);
				ps.setString(2, "demo"+i);
				ps.setString(3, "ps1234"+i);
				ps.addBatch();//将三个？替换的值添加到批处理
			}
			/*
			 * 批处理处理后返回的int数组中
			 * 保存的是每一条语句执行后影响数据库的条数
			 */
			ps.executeBatch();
			System.out.println(ps.executeBatch().toString());
			conn.commit();//提交事务
		} catch (Exception e) {
			//插入要么成功要么失败
			conn.rollback();
		}finally{
			conn.setAutoCommit(true);
		}
             //其他数据库操作
		ps.close();
		conn.close();
	}

}
