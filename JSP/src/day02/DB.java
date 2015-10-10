package day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DB {
    private static  Connection conn=null;
    private static Statement stmt=null;
    //构造函数中完成对数据库进行初始化
    public DB() {
        //创建连接
        conn=DB.getConnection();
    }
    ///建立与数据的连接
    public synchronized static Connection getConnection()
    {
        try {
        	Class.forName("oracle.jdbc.OracleDriver");// 加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }    
        try {
        	conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "123");//建立连接
            System.out.println("连接成功");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("数据库连接失败！");
        }   
        return conn;        
    }
    //关闭与访问数据库有关连接
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
