package spring;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;


/**
 * ���ݿ�������
 * @author Administrator
 *
 */
public class DBUtil {
	public static String driver;
	public static String url;
	public static String username;
	public static String password;
	public static BasicDataSource bds;
	/*
	 * ��̬��
	 * �����ڵ�һ�α�����ʱ֪�кϣ��ÿ�Ĵ���֮����ִ��
	 */
	static {
		   /*
		    * 1:ʵ����properties java.util.properties
		    * 2:��ȡ�����ļ�db��properties
		    * 3:����key��ȡ��Ӧ��value����=�������key���ұ���value
		    */
		Properties pr=new Properties();
		try {
			//ͨ���ļ���������ȡ�����ļ�����
			pr.load(new FileInputStream("db.properties"));
			/*
			 * ��ȡ��properties����Ὣ�����ļ������ݽ���Ϊһ������map����ʽ�����Ը���key
			 * ��ȡ��Ӧvalueֵ
			 */
			driver=pr.getProperty("driver");
			url=pr.getProperty("url");
			username=pr.getProperty("username");
			password=pr.getProperty("password");
			//��ʼ�����ӳ�
			bds=new BasicDataSource();
			//��������
			bds.setDriverClassName(driver);
			//����url
			bds.setUrl(url);
			//�����û���
			bds.setUsername(username);
			//��������
			bds.setPassword(password);
			/*
			 * ��ʼ������
			 * ���������
			 * ������������
			 * ��С����������
			 */
			//���ó�ʼ������
			bds.setInitialSize(1);
			//�������������
			bds.setMaxActive(1);
			//������С����������
			bds.setMaxIdle(1);
			//����������������
			/*ͨ��������������ҪС�������������
			 * �����𲻵�ɾ������ռ������ͷ���Դ������*/
//			bds.setMinIdle(20);
			//���û��ճ�ʱʱ�䣬�Ժ���Ϊ��λ
			bds.setMaxWait(6000);
			System.out.println("���ӳɹ�");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//ʵ�ֽӿڷ���
	 public synchronized static Connection getConnection() throws Exception {
		 try {
			return bds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
    public static  void closeConnection(Connection conn){
    	try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
}
    
    
    public static void main(String[] args) {
    	Connection conn=null;
//    	Statement stmt=null;
    	ResultSet rs=null;
//    	int flag=-1;
    	 try {
//    		 System.out.println("�������û���");
//    		 Scanner sc=new Scanner(System.in);
//    		 String inname=sc.nextLine();
//    		 System.out.println("����������");
//    		 String inpass=sc.nextLine();
			 conn=getConnection();
//			 stmt= conn.createStatement();
			 /*
			  * "select * from user_sdw where username='"+inname+"' and password='"+inpass+"'"
			  * "insert into emp(empno,ename,job,sal)values(1001,'������','manager',9500)"
			  */
//			 flag=stmt.executeUpdate("update emp set ename='����' where empno=1001");
//			 if(flag>0){
//				 System.out.println(flag);
//			 }
//			 rs=stmt.executeQuery("select * from user_sdw where username='"+inname+"' and password='"+inpass+"'");
			 /*����Ԥ����
			  * "insert into user_sdw(id,username,password) values(?,?,?,)"
			  */
//			 PreparedStatement ps =conn.perpareStatement("select * from user_sdw where username=? and password=?")
			 PreparedStatement ps=conn.prepareStatement("insert into user_sdw(id,username,password) values(?,?,?)");
//			 ps.setString(1, inname);
//			 ps.setString(2, inpass);
			 ps.setInt(1, 5);
			 ps.setString(2, "����");
			 ps.setString(3, "123456");
//			 rs=ps.executeQuery();
			 int flag=ps.executeUpdate();
//			 if(rs.next()){
//				System.out.println("��ӭ����"+rs.getString("realname"));
//			 }else{
//				 System.out.println("�˻�������");
//			 }
			 if(flag>0){
				 System.out.println("����ɹ�");
			 }
			 ps.close();
			 closeConnection(conn);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(conn!=null){
				DBUtil.closeConnection(conn);
			}
		}
   }
}