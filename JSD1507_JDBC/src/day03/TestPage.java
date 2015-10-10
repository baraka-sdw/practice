package day03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import day01.DBUtil;

/**
 * 通过JDBC实现分页查询
 * @author Administrator
 *
 */
public class TestPage {
	

	public static void main(String[] args) throws Exception {
		Scanner sc=new Scanner(System.in);
		System.out.println("请输入第几页");
		int pageSize=sc.nextInt();
		int page=sc.nextInt();
		/*
		 * 分页三步骤：
		 * 1：分页
		 * 2：编号
		 * 3：取值范围
		 * "select * from(select rownum rn,u.*
              from (select * from user_sdw order by id) u)
                 where rn between 11 and 20"
		 */
//		StringBuffer sb =new StringBuffer();
//		sb.append("select * from (");
//		sb.append("select rownum rn,u.*");
//		sb.append("from (select * from user_sdw order by id) u");
//		sb.append(" )where rn between ? and ?");
        String sql="select * from(select rownum rn,u.* from (select * from user_sdw order by id) u)where rn between ? and ?";
        int begin=(page-1)*pageSize+1;
        int end=begin+pageSize-1;
        Connection conn=DBUtil.getConnection();
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setInt(1, begin);
        ps.setInt(2, end);
        
        ResultSet rs=ps.executeQuery();
        
        while(rs.next()){
        	System.out.print(rs.getString("password"));
        	System.out.print(":");
            System.out.println(rs.getString("username"));
        }
        rs.close();
        ps.close();
        DBUtil.closeConnection(conn);
	}

}
