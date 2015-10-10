package dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Application;

import entity.Employee;
import entity.User;

public class ActionServlet extends HttpServlet {

	@SuppressWarnings("unused")
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();// JSPWriter
		String uri = request.getRequestURI();
		EmployeeDAO dao = new EmployeeDAO();
		// lastIndexOf从后往前数
		uri = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
		// 判断截取后的操作名，进行不同动作的分发操作
		if (uri.equals("list")) {
			// 查询
			// try {
			//
			// List<Employee> emps =dao.findAll();
			// dao.findAll();
			// out.println("<html>");
			// out.println("<head>");
			// out.println("<style type='text/css'>td{border:2px solid green}</style>");
			// out.println("</head>");
			// out.println("<body>");
			// out.println("<form>");
			// out.println("<fieldset><legend>员工信息列表</legend><br>");
			// out.println("<table style='border :2px solid green,border-collapse:true,cellpadding:50px,cellsapcing:0px'>");
			// out.println("<tr>");
			// out.println("<td>编号</td> <td>姓名</td> <td>薪水</td><td>年龄</td><td>操作&nbsp&nbsp&nbsp</td>");
			// out.println("</tr>");
			// HttpSession session=request.getSession();
			// for(Employee emp:emps){
			//
			// session.setAttribute("name"+emp.getId(),emp.getName());
			// out.println("<tr>");
			// out.println("<td>"+emp.getId()+"</td>"+"<td>"+emp.getName()+"</td>"+"<td>"+emp.getSalary()+"</td>"+"<td>"+emp.getAge()+"</td>");
			// out.println("<td><a href='delete.do?id="+emp.getId()+"'"+" onclick=\"return confirm"+"('是否确认删除"+emp.getName()+"?')\">删除"+"</a>");
			// out.println("<a href='ModifyEmp.jsp?id="+emp.getId()+"&salary="+emp.getSalary()+"&age="+emp.getAge()+"'"+">修改</a></td>");//+"&name="+emp.getName()
			// out.println("</tr>");
			// }
			// out.println("</table>");
			// out.println("</fieldset>");
			// out.println("<a href='emp.jsp'>添加员工</a>");
			// out.println("</form>");
			// out.println("</body>");
			// out.println("</html>");
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			try {
				request.getRequestDispatcher("ListEmp.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else if (uri.equals("add")) {
			// 添加
			try {
				Employee emp = new Employee();
				String name = request.getParameter("name");
				float salary = Float.parseFloat(request.getParameter("salary"));
				int age = Integer.parseInt(request.getParameter("age"));
				emp.setName(name);
				emp.setSalary(salary);
				emp.setAge(age);
				dao.add(emp);
				request.setAttribute("add", "1");
				request.getRequestDispatcher("ListEmp.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (uri.equals("delete")) {
			try {
				Employee emp = new Employee();
				int id = Integer.parseInt(request.getParameter("id"));
				emp.setId(id);
				dao.delete(emp);
				request.setAttribute("delete", "1");
				request.getRequestDispatcher("ListEmp.jsp").forward(request,
						response);
			} catch (Exception e) {
				e.printStackTrace();

			}
		} else if (uri.equals("modify")) {
			try {
				Employee emp = new Employee();
				int id = Integer.parseInt(request.getParameter("id"));
				String name = request.getParameter("name");
				float salary = Float.parseFloat(request.getParameter("salary"));
				int age = Integer.parseInt(request.getParameter("age"));
				emp.setId(id);
				emp.setName(name);
				emp.setSalary(salary);
				emp.setAge(age);
				dao.modify(emp);
				request.setAttribute("modify", "1");
				request.getRequestDispatcher("ListEmp.jsp").forward(request,
						response);
				// response.sendRedirect("ListEmp.jsp");
			} catch (Exception e) {
				e.printStackTrace();

				request.setAttribute("error", "系统繁忙");
				// 1
				request.getRequestDispatcher("error.jsp").forward(request,
						response);
				// 2
				// throw new ServletException();
			}
		} else if (uri.equals("login")) {
			String username = request.getParameter("username");
			String pwd = request.getParameter("password");
		    System.out.println(username+":"+pwd);
			try {
				HttpSession session = request.getSession();
				session.setAttribute("uname", username);
				String code = request.getParameter("vcode");
				User user=dao.findAllByName(username);
				if (user==null){
						request.setAttribute("login_name_error", "请检查用户名是否书写正确");
						request.getRequestDispatcher("login.jsp").forward(request, response);
						return;
					}else if(!pwd.equals(user.getPwd())) {
						request.setAttribute("login_pwd_error", "请检查密码是否书写正确");
						request.getRequestDispatcher("login.jsp").forward(request, response);
						return;
					}else if(!code.equals(session.getAttribute("code").toString())) {
						request.setAttribute("login_code_error", "请检查验证码是否书写正确");
						request.getRequestDispatcher("login.jsp").forward(request, response);
						return;
					}
					else if(code.equals(session.getAttribute("code"))){
					request.setAttribute("msg", "1");
					request.getRequestDispatcher("ListEmp.jsp").forward(request, response);
//					response.sendRedirect(response.encodeRedirectURL("ListEmp.jsp"));//URL重写
					return;
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (uri.equals("regist")) {
			User user = null;
			String username = request.getParameter("username");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String gender = request.getParameter("sex");
			try {
				User u = dao.findAllByName(username);
				if(username!=""){
				if (u != null) {
				} else {
					user = new User();
					user.setUserName(username);
					user.setPwd(pwd);
					user.setName(name);
					user.setGender(gender);
					dao.addUser(user);
					request.setAttribute("regist", "1");
					request.getRequestDispatcher("login.jsp").forward(request,
							response);
				  }
				}else {
					request.setAttribute("namenull", "用户名不能为空");
					request.getRequestDispatcher("regist.jsp").forward(request,
							response);
				}
			} catch (Exception e) {
				request.setAttribute("regist_error", "用户名已存在");
				request.getRequestDispatcher("regist.jsp").forward(request,
						response);
			}

		}else if(uri.equals("logout")){
			request.getSession().invalidate();
			response.sendRedirect("login.jsp");
		}
	}
}
