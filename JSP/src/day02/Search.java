package day02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class Search extends HttpServlet {

	
	private static final long serialVersionUID = -7022343632393907670L;

	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=gbk");
		PrintWriter out=resp.getWriter();
		out.println("123");
	}
      
}
