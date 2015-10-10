package util;
import java.sql.*;
/**
 * 封装了获得jdbc connection操作
 * @author teacher
 *
 */
public class DBUtil {
	public static Connection getConnection(){
		Connection conn = null;
		String driverClassName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sd1004db2?useUnicode=true&characterEncoding=utf8";
		String user = "root";
		String password="1234";
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
