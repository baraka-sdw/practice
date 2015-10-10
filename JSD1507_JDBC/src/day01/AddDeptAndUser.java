package day01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * ���һ�����ţ�ͬʱΪ����Ӽ����û�
 * @author Administrator
 *
 */
public class AddDeptAndUser {
	public static void main(String[] args) throws Exception {
		Connection conn=DBUtil.getConnection();
		conn.setAutoCommit(false);
		try {
			 
			
			//1���ű��в������� department_deptno_seq.nextval,'IT','����'
			String sql="insert into department(deptno ,dname,loc) values(department_deptno_seq.nextval,?,?)";
			/**connection���ص�preparstatement����
			 * ��һ������ΪԤ����sql
			 * �ڶ���������һ���ַ������飬����ָ��
			 * ��ʹ�ø�sql����һ�����ݺ󣬸������ݵ���Щ�ֶε�ֵ��Ҫ��ȡ
			 */
		
			PreparedStatement stmt =conn.prepareStatement(sql,new String[]{"deptno"} );
			stmt.setString(1,"IT" );
			stmt.setString(2, "����");
			stmt.addBatch();
			stmt.executeBatch();
			//ʹ�����е�ֵ��Ϊ�������ű������Ϣ
			//ִ�в������
			/*
			 * �÷����������ǻ�ȡ�ող�������������ǹ��ĵ��ֶε�ֵ
			 */
			 ResultSet rs=stmt.getGeneratedKeys();
			 ResultSetMetaData rsm=rs.getMetaData();
			 System.out.println(rsm.getColumnName(1));
			
			rs.next();
			int id=rs.getInt(1);
			/*
			 * 1:resultset.getInt(int index)
			 * resultset.getInt(String name)
			 */
			System.out.println("���ɵ�����ֵ��"+id);
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();

		}finally{
			conn.setAutoCommit(true);
		}
	}

}
