package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import toolBean.DB;
import valueBean.User;

public class AddUser extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//������ҳ
	String goBack="<br><a href='javascript:window.history.go(-1);'>������ҳ</a>";		
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();	
		
		String username=request.getParameter("username");
		if(username==null||username.equals(""))
		{
			out.print("�û�������Ϊ�գ�");
			out.print(goBack);			
		}
		String password=request.getParameter("password");
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		
		DB userDB=new DB();
		if(userDB.exists(user.getUsername()))
		{
			out.print("�û����Ѵ��ڣ�");
			out.print(goBack);
			return;
		}
		try {
			userDB.Add(user);
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("���ʧ��:"+e.getLocalizedMessage());
			out.print(goBack);
			return;
		}
		out.print("��ӳɹ���");
		out.print(goBack);
		out.close();
	}
}
