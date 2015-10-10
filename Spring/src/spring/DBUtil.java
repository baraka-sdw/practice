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
 * 数据库连接类
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
	 * 静态块
	 * 该类在第一次被加载时知行合，该块的代码之后不再执行
	 */
	static {
		   /*
		    * 1:实例化properties java.util.properties
		    * 2:读取配置文件db。properties
		    * 3:根据key获取对应的value，“=”左边是key，右边是value
		    */
		Properties pr=new Properties();
		try {
			//通过文件输入流读取配置文件内容
			pr.load(new FileInputStream("db.properties"));
			/*
			 * 读取后，properties对象会将配置文件的内容解析为一个类似map的形式，可以根据key
			 * 获取对应value值
			 */
			driver=pr.getProperty("driver");
			url=pr.getProperty("url");
			username=pr.getProperty("username");
			password=pr.getProperty("password");
			//初始化连接池
			bds=new BasicDataSource();
			//设置驱动
			bds.setDriverClassName(driver);
			//设置url
			bds.setUrl(url);
			//设置用户名
			bds.setUsername(username);
			//设置密码
			bds.setPassword(password);
			/*
			 * 初始连接数
			 * 最大连接数
			 * 最大空闲连接数
			 * 最小空闲连接数
			 */
			//设置初始连接数
			bds.setInitialSize(1);
			//设置最大连接数
			bds.setMaxActive(1);
			//设置最小空闲连接数
			bds.setMaxIdle(1);
			//设置最大空闲连接数
			/*通常最大空闲连接数要小于最大连接数的
			 * 否则起不到删除多余空间连接释放资源的作用*/
//			bds.setMinIdle(20);
			//设置回收超时时间，以毫秒为单位
			bds.setMaxWait(6000);
			System.out.println("连接成功");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//实现接口方法
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
//    		 System.out.println("请输入用户名");
//    		 Scanner sc=new Scanner(System.in);
//    		 String inname=sc.nextLine();
//    		 System.out.println("请输入密码");
//    		 String inpass=sc.nextLine();
			 conn=getConnection();
//			 stmt= conn.createStatement();
			 /*
			  * "select * from user_sdw where username='"+inname+"' and password='"+inpass+"'"
			  * "insert into emp(empno,ename,job,sal)values(1001,'张三丰','manager',9500)"
			  */
//			 flag=stmt.executeUpdate("update emp set ename='张三' where empno=1001");
//			 if(flag>0){
//				 System.out.println(flag);
//			 }
//			 rs=stmt.executeQuery("select * from user_sdw where username='"+inname+"' and password='"+inpass+"'");
			 /*测试预编译
			  * "insert into user_sdw(id,username,password) values(?,?,?,)"
			  */
//			 PreparedStatement ps =conn.perpareStatement("select * from user_sdw where username=? and password=?")
			 PreparedStatement ps=conn.prepareStatement("insert into user_sdw(id,username,password) values(?,?,?)");
//			 ps.setString(1, inname);
//			 ps.setString(2, inpass);
			 ps.setInt(1, 5);
			 ps.setString(2, "赵六");
			 ps.setString(3, "123456");
//			 rs=ps.executeQuery();
			 int flag=ps.executeUpdate();
//			 if(rs.next()){
//				System.out.println("欢迎回来"+rs.getString("realname"));
//			 }else{
//				 System.out.println("账户不存在");
//			 }
			 if(flag>0){
				 System.out.println("插入成功");
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