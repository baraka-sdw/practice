package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;

import day01.DBUtil;

/**
 * 向user表中插入测试数据
 * @author Administrator
 *
 */
public class TestUInsert {

	public static void main(String[] args) throws Exception {
		Connection conn=DBUtil.getConnection();
		String sql ="insert into user_sdw(id,username,password) values(?,?,?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		for(int i=1;i<20;i++){
			ps.setInt(1, i);
			ps.setString(2, "用户"+i);
			ps.setString(3, "密码"+i);
			ps.addBatch();
		}
             ps.executeBatch();
             ps.close();
            DBUtil.closeConnection(conn);
	}

}
