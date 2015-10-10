package day02;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddCookieServlet extends HttpServlet {

	
	private static final long serialVersionUID = 8536766977149868009L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		PrintWriter out=resp.getWriter();
		HttpSession session=req.getSession();
//		session.setAttribute("姓名", "张三");
		out.println("姓名"+session.getAttribute("姓名").toString()+"..");
//		out.println(session.getId().length());
//		System.out.println(session.getId());
//		session.getAttribute("");
//		Cookie cookieo=new Cookie(URLEncoder.encode("姓名", "utf-8"), URLEncoder.encode("张三", "utf-8"));
//		Cookie cookiet=new Cookie(URLEncoder.encode("地址", "utf-8"), URLEncoder.encode("火星", "utf-8"));
//		cookieo.setMaxAge(600);
//		cookiet.setMaxAge(60000);
//		resp.addCookie(cookieo);
//		resp.addCookie(cookiet);
//		Cookie[] c= req.getCookies();
//		if(c!=null){
//			Cookie ck=c[1];
//			String value=ck.getValue();
//			value=URLDecoder.decode(value,"utf-8");
//			out.println(value);
//		}
//	    for (Cookie cookie : c) {
//	    	String name=cookie.getName();
//			out.println(cookie.getName());
//			out.println(cookie.getValue());
//			if(name.equals("name")){
//				cookie.setValue("zhangsan");
//				resp.addCookie(cookie);
//			}else if(name.equals("address")){
//				cookie.setValue("earth");
//				resp.addCookie(cookie);
//			}
//		}
		out.close();
	}
}
