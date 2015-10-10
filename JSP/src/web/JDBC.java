package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class JDBC extends HttpServlet {
    
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.service(req, resp);
		PrintWriter out=resp.getWriter();
		Connection conn;
		Statement stat;
		ResultSet rs;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.126:1521:xe", "system", "123");
			out.println("连接成功");
			stat=conn.createStatement();
		} catch (ClassNotFoundException e) {
		    out.println("少驱动");
			e.printStackTrace();
		} catch (SQLException e) {
			out.println("书写错误");
			e.printStackTrace();
		}
	}
   
}
