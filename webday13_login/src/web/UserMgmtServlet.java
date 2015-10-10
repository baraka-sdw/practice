package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DAOFactory;
import dao.UserDAO;

import entity.User;

public class UserMgmtServlet extends HttpServlet {

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String action = uri.substring(uri.lastIndexOf("/"),
				uri.lastIndexOf("."));
		if (action.equals("/regist")) {
			// step1 �ȱȽϱ��е���֤����session�е���֤��
			// �Ƿ�һ�£�ֻ��һ�£�������ע��.
			String number1 = request.getParameter("number");
			HttpSession session = request.getSession(false);
			String number2 = (String) session.getAttribute("number");
			if (number1.equals(number2)) {
				// ע��
				String username = request.getParameter("username");
				String pwd = request.getParameter("pwd");
				String sex = request.getParameter("sex");
				User user = new User();
				user.setUsername(username);
				user.setPassword(pwd);
				user.setSex(sex);
				UserDAO dao = (UserDAO) DAOFactory.getInstance(UserDAO.class);
				try {
					dao.save(user);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ServletException(e.getMessage());
				}
				response.sendRedirect("login.jsp");
				return;
			} else {
				// ��֤�벻��ȷ����ʾ�û���������
				request.setAttribute("regist_err", "��֤�벻��ȷ");
				request.getRequestDispatcher("regist.jsp").forward(request,
						response);
				return;
			}
		}// end regist
		if (action.equals("/login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("pwd");
			// �����û�������db������Ӧ��¼�����Ƚ�����
			UserDAO dao = (UserDAO) DAOFactory.getInstance(UserDAO.class);
			User user = null;
			try {
				user = dao.findByUsername(username);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (user == null) {
				request.setAttribute("login_err", "�û���������");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
				return;
			} else {
				if (user.getPassword().equals(password)) {
					// �û���¼�ɹ�,���û���Ϣ����session
					HttpSession session = request.getSession(false);
					session.setAttribute("user", user);
					response.sendRedirect("main.jsp");
					return;
				} else {
					request.setAttribute("login_err", "�������");
					request.getRequestDispatcher("login.jsp").forward(request,
							response);
					return;
				}
			}
		}
	}
}
