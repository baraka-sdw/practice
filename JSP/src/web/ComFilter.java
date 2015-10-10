package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestListener;
import javax.servlet.ServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;

public class ComFilter implements Filter {
    private String str;
    private int length;
	@Override
	public void destroy() {
	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String comm=req.getParameter("comment");
		PrintWriter out=resp.getWriter();
		if(comm.indexOf(str	)!=-1){
			out.println("<h1>你已被封号</h1>");
		}else if(comm.length()>length){
			out.println("字数过长");
		}else{
			chain.doFilter(req, resp);
		}
	}
	@Override
	public void init(FilterConfig config) throws ServletException {
		str=config.getInitParameter("illeagerStr");
		length=Integer.parseInt(config.getInitParameter("length"));
	}
}
