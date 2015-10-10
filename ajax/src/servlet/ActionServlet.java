package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SaleDao;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import entity.City;
import entity.Sale;

public class ActionServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"), uri.lastIndexOf("."));
		if (path.equals("/get_text")) {
			out.println("1234567");
		} else if (path.equals("/post_text")) {
			String name = req.getParameter("uname");
			System.out.println(name);
			out.println("come once again" + "...." + name);
		} else if (path.equals("/check")) {
			if (true) {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			String name = req.getParameter("uname");
			System.out.println(name);
			if (name.equals("zhangsan")) {
				out.println("�û����Ѵ���");
			} else if (name.equals("")) {
				out.println("����Ϊ��");
			} else {
				out.print("����");
			}
		} else if (path.equals("/getCity")) {
			String name = req.getParameter("name");
			if (name.equals("bj")) {
				out.print("����,cy;����,dc");
			} else if (name.equals("sh")) {
				out.print("����,ja;����,hp;�ֶ�,pd");
			} else if (name.equals("ah")) {
				out.print("�Ϸ�,hf;�ߺ�,wh;��ɽ,hs");
			}
		}else if(path.equals("/city")){
			String name=req.getParameter("name");
			System.out.println(name);
			if(name.equals("bj")){
				City c1=new City("����","hd");
				City c2=new City("����","dc");
				City c3=new City("�йش�","zgc");
				List<City> cl=new ArrayList<City>();
				cl.add(c1);
				cl.add(c2);
				cl.add(c3);
				JSONArray ja=JSONArray.fromObject(cl);
				out.println(ja.toString());
			}else if(name.equals("ah")){
				City c1=new City("�Ϸ�","hf");
				City c2=new City("�ߺ�","wh");
				City c3=new City("��ɽ","hs");
				List<City> cl=new ArrayList<City>();
				cl.add(c1);
				cl.add(c2);
				cl.add(c3);
				JSONArray ja=JSONArray.fromObject(cl);
				out.println(ja.toString());
			}
		}else if(path.equals("/sale")){
			SaleDao dao=new SaleDao();
			try {
				List<Sale> all= dao.findAll();
				JSONArray ja=JSONArray.fromObject(all);
				System.out.println(ja);
				out.println(ja);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.close();
	}
}
