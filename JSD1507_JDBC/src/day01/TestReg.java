package day01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TestReg {

	public static void main(String[] args) {
		/*
		 * 1:ͨ����ǰScanner��ȡ�û�����
		 * 2����ȡ����
		 * 3����ѯuser��ǰ���id
		 * 4�������id+1��������µ�id
		 * 5��ƴдinsert������
		 * 6��֪ͨ�û�ע����
		 * 7���ر�����
		 */
      Scanner sc=new Scanner(System.in);
		 System.out.println("�������û���");
		 String username=sc.nextLine();
		 System.out.println("����������");
		 String password=sc.nextLine();
		 Connection conn=null;
		 Statement stmt=null;
		 try {
			conn=DBUtil.getConnection();
			 stmt=conn.createStatement();
			 ResultSet rs=stmt.executeQuery("select max(id)+1 id from user_sdw");
			 int id=-1;
			 if(rs.next()){
				 id=rs.getInt("id");
			 }
			 //"insert into user_sdw(id,username,password) values("+id",'"username"','+"password+"')"
			 int flag=stmt.executeUpdate("insert into user_sdw(id,username,password) values(+"+id+","+"'"+username+"'"+","+"'"+password+"')");
			 if(flag>0){
				 System.out.println("ע��ɹ�");
				}else{
					System.out.println("ע��ʧ��");
				}
			 
		} catch (Exception e) {
			
	}

   }
}