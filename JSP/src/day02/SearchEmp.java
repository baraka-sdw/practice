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

public class SearchEmp extends HttpServlet {
    
	
	private static final long serialVersionUID = 6379775993968848830L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=gbk");
		PrintWriter out=response.getWriter();
		Connection conn=null;
	    ResultSet rs=null;
	    PreparedStatement ps=null;
		try {
			conn=DB.getConnection();
			ps=conn.prepareStatement("select * from t_emp");
			rs=ps.executeQuery();
			out.println("<html>");
			out.println("<head>");
			out.println("<style type='text/css'>td{border:2px solid green}</style>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form>");
			out.println("<fieldset><legend>Ա����Ϣ�б�</legend><br>");
			out.println("<table style='border :2px solid green,border-collapse:true,cellpadding:50px,cellsapcing:0px'>");
			out.println("<tr>");
			out.println("<td>���</td> <td>����</td> <td>нˮ</td><td>����</td><td>����&nbsp&nbsp&nbsp</td>");
			out.println("</tr>");
			while(rs.next()){
				int id=rs.getInt("id");
				String name=rs.getString("name");
				Double salary=rs.getDouble("salay");
				int age=rs.getInt("age");
				out.println("<tr>");
				out.println("<td>"+id+"</td>"+"<td>"+name+"</td>"+"<td>"+salary+"</td>"+"<td>"+age+"</td>");
				out.println("<td><a href='detete.do?id="+id+"'"+" onclick=\"return confirm"+"('�Ƿ�ȷ��ɾ��"+name+"?')\">ɾ��"+"</a>");
				out.println("<a href='ModifyEmp.jsp?id="+id+"'"+">�޸�</a></td>");

				
//				out.println("<td>" +
//						"<a href='del?id="+id+"' " +
//					    "onclick=\"return confirm" +
//					    "('�Ƿ�ȷ��ɾ��"+name+"��')\">ɾ��" +
//					    "</a>");
//				//�����޸ĵ�����
//				out.println("<a href" +
//				"='load?id="+id+"'>�޸�</a>");
//				out.println("</td></tr>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</fieldset>");
			out.println("<a href='emp.jsp'>���Ա��</a>");
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
		
	}
    
}
