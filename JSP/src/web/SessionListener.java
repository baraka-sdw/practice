package web;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
	private int count=0;
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		count++;
		HttpSession session=se.getSession();
		ServletContext sc= session.getServletContext();
		sc.setAttribute("count", count);
		System.out.println("´´½¨");
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		count--;
		HttpSession session=se.getSession();
		ServletContext sc= session.getServletContext();
		sc.setAttribute("count", count);
		System.out.println("Ïú»Ù");
	}
   
}
