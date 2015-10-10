package day02;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ListEmpServlet 
			extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void service(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,IOException{
		//1.设置输出格式
		response.setContentType
			("text/html;charset=UTF-8");
		//2.获取输出流
		PrintWriter out = 
			response.getWriter();
		//3.使用JDBC查询数据
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			//加载驱动
			Class.forName
			 ("oracle.jdbc.OracleDriver");
			//获取连接对象
			con = DriverManager.
				getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",
				"system",
				"123");
			//创建命令对象
			ps = con.prepareStatement
				("select * from t_emp");
			//执行语句后得到结果集
			rs = ps.executeQuery();
			//输出一部分html标记
			out.println(
			"<html><head></head><body>" +
			"<table><tr><td>编号</td><td>姓名</td>" +
			"<td>薪水</td><td>年龄</td>" +
			"<td>操作</td></tr>");
			//遍历结果集
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double salary = rs.getDouble("salay");
				int age = rs.getInt("age");
				out.println("<tr>");
				out.println("<td>"+id+"</td>");
				out.println("<td>"+name+"</td>");
				out.println("<td>"+salary+"</td>");
				out.println("<td>"+age+"</td>");
				//添加删除链接时，需要隐藏每一条记录的ID
				out.println("<td>" +
						"<a href='del?id="+id+"' " +
					    "onclick=\"return confirm" +
					    "('是否确认删除"+name+"？')\">删除" +
					    "</a>");
				//增加修改的链接
				out.println("<a href" +
				"='load?id="+id+"'>修改</a>");
				out.println("</td></tr>");
			}
			out.println("</table>" +
					"<a href='addEmp.html'>增加员工</a>" +
					"</body></html>");
			out.close();
		}catch(Exception e){
			out.print("出错");
			e.printStackTrace();
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {	
					e.printStackTrace();
				}
			}
		}
		
	}
}