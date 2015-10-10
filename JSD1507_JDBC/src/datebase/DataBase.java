package datebase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import day01.DBUtil;

public class DataBase {
	Connection conn=null;
	Statement stmt=null;
	ResultSet rs=null;
	public DataBase() {
		
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			try {
				 conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");
				 stmt=conn.createStatement();
				 rs=stmt.executeQuery("select * from emp");
				while(rs.next()){
					System.out.println(rs.getString("name"));
				}
				conn.close();
				stmt.close();
				rs.close();
				
			} catch (SQLException e) {
				System.out.println("����ʧ��");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
   public static void main(String[] args) {
	DataBase db=new DataBase();
	 try {
   	  Connection conn =DBUtil.getConnection();
   	  System.out.println(conn);
         System.out.println("�Ѿ���ȡ������");
         DBUtil.closeConnection(conn);
         System.out.println("�ر�������");
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}