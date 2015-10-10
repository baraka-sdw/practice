package day02;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ListEmpServlet 
			extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void service(
		HttpServletRequest request,
		HttpServletResponse response)
		throws ServletException,IOException{
		//1.���������ʽ
		response.setContentType
			("text/html;charset=UTF-8");
		//2.��ȡ�����
		PrintWriter out = 
			response.getWriter();
		//3.ʹ��JDBC��ѯ����
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			//��������
			Class.forName
			 ("oracle.jdbc.OracleDriver");
			//��ȡ���Ӷ���
			con = DriverManager.
				getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",
				"system",
				"123");
			//�����������
			ps = con.prepareStatement
				("select * from t_emp");
			//ִ������õ������
			rs = ps.executeQuery();
			//���һ����html���
			out.println(
			"<html><head></head><body>" +
			"<table><tr><td>���</td><td>����</td>" +
			"<td>нˮ</td><td>����</td>" +
			"<td>����</td></tr>");
			//���������
			while(rs.next()){
				int id = rs.getInt("id");
				String name = rs.getString("name");
				double salary = rs.getDouble("salay");
				int age = rs.getInt("age");
				out.println("<tr>");
				out.println("<td>"+id+"</td>");
				out.println("<td>"+name+"</td>");
				out.println("<td>"+salary+"</td>");
				out.println("<td>"+age+"</td>");
				//���ɾ������ʱ����Ҫ����ÿһ����¼��ID
				out.println("<td>" +
						"<a href='del?id="+id+"' " +
					    "onclick=\"return confirm" +
					    "('�Ƿ�ȷ��ɾ��"+name+"��')\">ɾ��" +
					    "</a>");
				//�����޸ĵ�����
				out.println("<a href" +
				"='load?id="+id+"'>�޸�</a>");
				out.println("</td></tr>");
			}
			out.println("</table>" +
					"<a href='addEmp.html'>����Ա��</a>" +
					"</body></html>");
			out.close();
		}catch(Exception e){
			out.print("����");
			e.printStackTrace();
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {	
					e.printStackTrace();
				}
			}
		}
		
	}
}