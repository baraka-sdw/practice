package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter  out=resp.getWriter();
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String path=req.getRequestURI();
		String name=path.substring(path.lastIndexOf("/"), path.lastIndexOf("."));
		if(name.equals("/getInfo")){
			String names=req.getParameter("name");
			System.out.println(names);
			out.println("hello");
		}
	}
       
}
