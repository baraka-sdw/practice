package util;

import java.sql.*;

/**
 * 封装了获得jdbc connection操作
 * 
 * @author teacher
 * 
 */
public class DBUtil {
	public static Connection getConnection() {
		Connection conn = null;
		String driverClassName = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe?useUnicode=true&characterEncoding=utf8";
		String user = "system";
		String password = "123";
		try {
			Class.forName(driverClassName);
			conn = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
