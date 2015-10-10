package day02;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *Servlet����������
 * @author Administrator
 *
 */
public class LifeServlet extends HttpServlet {

	private static final long serialVersionUID = 5176186336905700428L;
	public LifeServlet() {
		System.out.println("1.Constructor is running");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("3.Servlet is running");
		ServletConfig config=getServletConfig();
		String name =config.getInitParameter("name");
		String address=config.getInitParameter("address");
		String sname=config.getServletName();
		System.out.println("servlet���ƣ�"+sname);
		System.out.println("��ʼ�����ƣ�"+name);
		System.out.println("��ʼ����ַ��"+address);
	}
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("4.Servlet is destory ");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("2.Init is runnin	g");
	}
}
