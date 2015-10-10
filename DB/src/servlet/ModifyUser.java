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

public class ModifyUser extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 返回上页
		String goBack = "<br><a href='javascript:window.history.go(-1);'>返回上页</a>";

		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		DB dbUser = new DB();
		try {
			dbUser.Update(user);
		} catch (SQLException e) {
			e.printStackTrace();
			out.print("修改失败：" + e.getLocalizedMessage());
			out.print(goBack);
			return;
		}

		out.print("修改成功！");
		out.print(goBack);
		out.close();
	}
}
