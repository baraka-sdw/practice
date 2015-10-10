package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
     public static Connection getConnection() throws SQLException {
    	 Connection conn = null;
    	 try {
         	Class.forName("oracle.jdbc.OracleDriver");// �������ݿ�����
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }    
         try {
         	conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");
             System.out.println("���ӳɹ�");
         } catch (SQLException e) {
             e.printStackTrace();
             System.out.print("���ݿ�����ʧ�ܣ�");
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
