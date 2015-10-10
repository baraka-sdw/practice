package day01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TestReg {

	public static void main(String[] args) {
		/*
		 * 1:通过当前Scanner获取用户输入
		 * 2：获取连接
		 * 3：查询user表当前最大id
		 * 4：对这个id+1，计算出新的id
		 * 5：拼写insert语句插入
		 * 6：通知用户注册结果
		 * 7：关闭连接
		 */
      Scanner sc=new Scanner(System.in);
		 System.out.println("请输入用户名");
		 String username=sc.nextLine();
		 System.out.println("请输入密码");
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
				 System.out.println("注册成功");
				}else{
					System.out.println("注册失败");
				}
			 
		} catch (Exception e) {
			
	}

   }
}