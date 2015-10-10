package web;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.xml.bind.JAXB;


public class HelloServlet extends HttpServlet {
	PrintWriter pw;
	Date nowday;
	SimpleDateFormat sdf;
	@Override
	public void service(HttpServletRequest req,  HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("gbk");
		 pw =resp.getWriter();
		//获取所有消息头的名字及内容
//		Enumeration enu=req.getHeaderNames();
		//循环获取每一个元素
//		while(enu.hasMoreElements()){
//			String name=enu.nextElement().toString();
//			String value=req.getHeader("name");
//			System.out.println(name+":"+value);
//		}
		 String name=req.getParameter("a");
		 pw.println("<h1>"+name+"</h1>");
//			 sdf=new SimpleDateFormat("HH:mm:ss");
//			 nowday=new Date();
//			 String day=sdf.format(nowday);
//			 pw.println("<b>"+day+"<b>");
		
         	 
//		Timer time=new Timer();
//		time.schedule(new TimerTask() {
//			
//			@Override
//			public void run() {
//				nowday=new Date();
//				String day=sdf.format(nowday);
//				pw.println("<b>"+day+"<b>");
//				System.out.println(day);
//			}
//		}, 0,1000);
//		sdf=new SimpleDateFormat("HH:mm:ss");
//		Thread t= new Thread(){
//			@Override
//			
//			public void run() {
//				while(true){
//					nowday=new Date();
//					String day=sdf.format(nowday);
//					pw.println(day);
//					try {
//						sleep(1000);
//					} catch (Exception e) {
//					}
//				}
//			}
//		};
//		t.start();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		 super.doGet(req, resp);
		 resp.setContentType("text/html;charset=utf-8");
		 PrintWriter pw=resp.getWriter();
		 String s=req.getParameter("name");
		 pw.println("<h1>123"+s+"</h1>");
		 
	}
	
}
