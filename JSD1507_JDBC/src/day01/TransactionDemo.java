package day01;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionDemo {
/*
 * �������
 */
	public static void main(String[] args) throws Exception {
		Connection conn=DBUtil.getConnection();
		String sql="insert into user_sdw"+"(id,username,password)"+"values(?,?,?)";
		PreparedStatement ps=conn.prepareStatement(sql);
		//�Զ��ύ�ر�
		conn.setAutoCommit(false);
		try {
			for(int i=10;i<601;i++){
				ps.setInt(1, i);
				ps.setString(2, "demo"+i);
				ps.setString(3, "ps1234"+i);
				ps.addBatch();//���������滻��ֵ��ӵ�������
			}
			/*
			 * ��������󷵻ص�int������
			 * �������ÿһ�����ִ�к�Ӱ�����ݿ������
			 */
			ps.executeBatch();
			System.out.println(ps.executeBatch().toString());
			conn.commit();//�ύ����
		} catch (Exception e) {
			//����Ҫô�ɹ�Ҫôʧ��
			conn.rollback();
		}finally{
			conn.setAutoCommit(true);
		}
             //�������ݿ����
		ps.close();
		conn.close();
	}

}
