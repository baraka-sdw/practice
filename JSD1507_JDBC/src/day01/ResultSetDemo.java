package day01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetDemo {
            public static void main(String[] args) throws Exception {
				Connection conn=DBUtil.getConnection();
				Statement stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
				String sql="select * from user_sdw";
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next()){
					System.out.println(rs.getString("username")+rs.getString("password"));
					System.out.println("5ÃëºóÐÞ¸Ä");
					Thread.sleep(5000);
					rs.updateString(1, "");
				}
				
			}
} 
