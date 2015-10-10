package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
     public static Connection getConnection() throws SQLException {
    	 Connection conn = null;
    	 try {
         	Class.forName("oracle.jdbc.OracleDriver");// 加载数据库驱动
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }    
         try {
         	conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");
             System.out.println("连接成功");
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.print("数据库连接失败！");
             throw e;
             
         }   
         return conn;     
	}
     public static void close(Connection conn) throws Exception{
    	 if(conn!=null){
    		 try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
    	 }
     }
}
