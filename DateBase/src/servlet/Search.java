package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import toolBean.DB;
import valueBean.User;

public class Search extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			String username = request.getParameter("username");			
			DB db = new DB();
			User user1=null;
			try {
				user1 = db.searchByUsername(username);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		request.getSession().setAttribute("user",user1);
		response.sendRedirect("searchEdit.jsp");
	}

}
