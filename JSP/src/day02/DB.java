package day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DB {
    private static  Connection conn=null;
    private static Statement stmt=null;
    //���캯������ɶ����ݿ���г�ʼ��
    public DB() {
        //��������
        conn=DB.getConnection();
    }
    ///���������ݵ�����
    public synchronized static Connection getConnection()
    {
        try {
        	Class.forName("oracle.jdbc.OracleDriver");// �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }    
        try {
        	conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");//��������
            System.out.println("���ӳɹ�");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("���ݿ�����ʧ�ܣ�");
        }   
        return conn;        
    }
    //�ر���������ݿ��й�����
    public static void finalize(Connection conn)   
    {
        try {
            if(stmt!=null)
                stmt.close();
            if(conn!=null)
                conn.close();
        }
            catch (SQLException e) {
        e.printStackTrace();
        }
    }   
}
