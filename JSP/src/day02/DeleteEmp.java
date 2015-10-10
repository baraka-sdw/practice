package day02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteEmp extends HttpServlet {

	private static final long serialVersionUID = -7891699631750300153L;

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=gbk");
		PrintWriter out=resp.getWriter();
		int id=Integer.parseInt(req.getParameter("id"));
		Connection conn=null;
	    PreparedStatement ps=null;
	    
	    try {
	    	conn=DB.getConnection();
			ps=conn.prepareStatement("delete t_emp where id=?");
			ps.setInt(1, id);
			int flag=ps.executeUpdate();
			if(flag>0){
				out.println("É¾³ý³É¹¦");
				resp.sendRedirect("SearchEmp");
			}else{
				out.println("É¾³ýÊ§°Ü");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
            
}
