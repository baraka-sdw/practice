package day02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContext extends HttpServlet {

	
	private static final long serialVersionUID = -3647461558057652934L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		PrintWriter out=response.getWriter();
		javax.servlet.ServletContext context=getServletContext();
		Object count=context.getAttribute("count");
		if(count==null){
			context.setAttribute("count", context.getInitParameter("count"));
		}else{
			context.setAttribute("count", Integer.parseInt(context.getAttribute("count").toString())+1);
		}
		out.println("ä¯ÀÀ´ÎÊý£º"+context.getAttribute("count"));
	}
}
