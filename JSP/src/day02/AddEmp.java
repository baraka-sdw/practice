package day02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AddEmp extends HttpServlet {
	Connection conn=null;
    Statement  stat=null;
    ResultSet rs=null;
	private static final long serialVersionUID = 1L;
	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		PrintWriter out = response.getWriter();
		String name=request.getParameter("name");
		double salay=Double.parseDouble(request.getParameter("salary"));
		int age=Integer.parseInt(request.getParameter("age"));
//		out.println(name+"<br>"+salay+"<br>"+age);

			PreparedStatement stmt=null;
			try {
				conn=DB.getConnection();
				stmt = conn.prepareStatement("insert into t_emp values(emp_id_seq.nextval,?,?,?)");
				stmt.setString(1, name);
				stmt.setDouble(2, salay);
				stmt.setInt(3, age);
				stmt.executeUpdate();
				 out.println("Ìí¼Ó³É¹¦");
				 response.sendRedirect("SearchEmp");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(conn!=null){
					DB.finalize(conn);
				}
			}
		out.flush();
		out.close();
	}
}
