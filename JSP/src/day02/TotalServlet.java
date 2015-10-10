package day02;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TotalServlet extends HttpServlet {

	private static final long serialVersionUID = -6452984520085940822L;
	Connection conn=null;
    ResultSet rs=null;
    PreparedStatement ps=null;
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		request.setCharacterEncoding("gbk");
		PrintWriter out=response.getWriter();
		String uri=request.getRequestURI();
		conn=DB.getConnection();
		if(uri.equals("/JSP/add.do")){
			String name=request.getParameter("name");
			double salay=Double.parseDouble(request.getParameter("salary"));
			int age=Integer.parseInt(request.getParameter("age"));
			try {
				ps = conn.prepareStatement("insert into t_emp values(emp_id_seq.nextval,?,?,?)");
				ps.setString(1, name);
				ps.setDouble(2, salay);
				ps.setInt(3, age);
				ps.executeUpdate();
				 out.println("添加成功");
				 response.sendRedirect("list.do");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(conn!=null){
					DB.finalize(conn);
				}
			}
		}else if(uri.equals("/JSP/list.do")){
			try {
				ps=conn.prepareStatement("select * from t_emp");
				rs=ps.executeQuery();
				out.println("<html>");
				out.println("<head>");
				out.println("<style type='text/css'>td{border:4px solid green;text-align:center}</style>");
				out.println("</head>");
				out.println("<body>");
				out.println("<form>");
				out.println("<fieldset><legend>员工信息列表</legend><br>");
				out.println("<table style='border :2px solid green;border-collapse:collapse;cellpadding:50px;cellsapcing:0px'>");
				out.println("<tr>");
				out.println("<td>编号</td> <td>姓名</td> <td>薪水</td><td>年龄</td><td>&nbsp&nbsp&nbsp操作&nbsp&nbsp&nbsp</td>");
				out.println("</tr>");
				while(rs.next()){
					int id=rs.getInt("id");
					String name=rs.getString("name");
					Double salary=rs.getDouble("salary");
					int age=rs.getInt("age");
					out.println("<tr>");
					out.println("<td>"+id+"</td>"+"<td>"+name+"</td>"+"<td>"+salary+"</td>"+"<td>"+age+"</td>");
					out.println("<td><a href='delete.do?id="+id+"'"+" onclick=\"return confirm"+"('是否确认删除"+name+"?')\">删除"+"</a>");
					out.println("<a href='ModifyEmp.jsp?id="+id+"'"+">修改</a></td>");

					
//					out.println("<td>" +
//							"<a href='del?id="+id+"' " +
//						    "onclick=\"return confirm" +
//						    "('是否确认删除"+name+"？')\">删除" +
//						    "</a>");
//					//增加修改的链接
//					out.println("<a href" +
//					"='load?id="+id+"'>修改</a>");
//					out.println("</td></tr>");
					out.println("</tr>");
				}
				out.println("</table>");
				out.println("</fieldset>");
				out.println("<a href='emp.jsp'>添加员工</a>");
				out.println("</form>");
				
				out.println("</body>");
				out.println("</html>");
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(conn!=null){
	              DB.finalize(conn);
				}
				if(ps!=null){
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if(rs!=null){
					try {
						rs.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}else if(uri.equals("/JSP/delete.do")){
			int id=Integer.parseInt(request.getParameter("id"));
			 try {
					ps=conn.prepareStatement("delete t_emp where id=?");
					ps.setInt(1, id);
					int flag=ps.executeUpdate();
					if(flag>0){
						out.println("删除成功");
						response.sendRedirect("/JSP/list.do");
					}else{
						out.println("删除失败");
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}else if(uri.equals("/JSP/modify.do")){
			int id=Integer.parseInt(request.getParameter("id"));
			String name=request.getParameter("name");
		     Double salary=Double.parseDouble(request.getParameter("salary"));
		     int age=Integer.parseInt(request.getParameter("age"));
			try {
				ps=conn.prepareStatement("update t_emp set name=?,salay=?,age=? where id=?");
				ps.setString(1, name);
				ps.setDouble(2, salary);
				ps.setInt(3, age);
				ps.setInt(4, id);
				int flag=ps.executeUpdate();
				if(flag>0){
					out.println("修改成功");
				}
				out.println("<a href=\"list.do\">返回员工列表</a>");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
