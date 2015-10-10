package day02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class ModifyEmp extends HttpServlet {
	
	private static final long serialVersionUID = 1297488626212660302L;

	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=gbk");
		req.setCharacterEncoding("gbk");
		PrintWriter out=resp.getWriter();
		int id=Integer.parseInt(req.getParameter("id"));
		Connection conn=null;
	    PreparedStatement ps=null;
	     String name=req.getParameter("name");
	     Double salary=Double.parseDouble(req.getParameter("salary"));
	     int age=Integer.parseInt(req.getParameter("age"));
	     conn=DB.getConnection();
		 try {
			ps=conn.prepareStatement("update t_emp set name=?,salay=?,age=? where id=?");
			ps.setString(1, name);
			ps.setDouble(2, salary);
			ps.setInt(3, age);
			ps.setInt(4, id);
			int flag=ps.executeUpdate();
			if(flag>0){
				out.println("修改成功");
			}
			out.println("<a href=\"SearchEmp\">返回员工列表</a>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
