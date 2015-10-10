package toolBean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import valueBean.User;


public class DB {
    private static  Connection conn=null;
    private static Statement stmt=null;
    //���캯������ɶ����ݿ���г�ʼ��
    public DB() {
        //��������
        conn=this.getConnection();
        try {
            //����Statement
            stmt=conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //������user��ӵ�����
    public void Add(User user) throws SQLException
    {
        String sql="INSERT INTO user(username,password) VALUES('"
            +user.getUsername()+"','"+user.getPassword()+"')";
        stmt.executeUpdate(sql);
    }
    //�����û������ؼ��֣������ݿ�ɾ����Ӧ�ļ�¼
    public void Delete(String username) throws SQLException
    {
        String sql="Delete From user Where username='"+username+"'";
        stmt.executeUpdate(sql);        
    }
    //�޸����ݿ��е�user
    public void Update(User user) throws SQLException
    {
        String sql="UPDATE user set password='"
            +user.getPassword()+"' where username='"+user.getUsername()+"'";
        stmt.executeUpdate(sql);        
    }
    //�����û����ƣ��ؼ��֣������ݿ��в��Ҽ�¼�������ҵ��ļ�¼д�����user�з���
    public User searchByUsername(String username) throws SQLException
    {
        User user=new User();
        String sql="select * from user where username='"+username+"'";
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            String name=rs.getString("username");
            if(name.equals(username))
            {
                //�������ݿ���ҵõ��ļ�¼�������user��
                user.setUsername(name);
                user.setPassword(rs.getString("password"));
                if(rs!=null)
                    rs.close();
                return user;
            }
        }
        return null;//���Ҳ�������null        
    }
    
    public List<User> search(String sql) throws SQLException
    {
        List<User> users=new ArrayList<User>();
        ResultSet rs=stmt.executeQuery(sql);
        while(rs.next())
        {
            String name=rs.getString("username");
            String pass=rs.getString("password");
            User user=new User(name,pass);
            users.add(user);           
        }
        return users;   
    }
    //�����û��жϸü�¼�Ƿ����
    public boolean exists(String username)
    {
        boolean rtn=false;
        try {
            if(searchByUsername(username)!=null)
                rtn=true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return rtn;
    }
    ///���������ݵ�����
    private Connection getConnection()
    {
        String driverClass="com.mysql.jdbc.Driver";
        String url=//�봴�����ݿ�Ĵ����ϣ���֤�������ݿ�ʱ����������
"jdbc:mysql://localhost:3306/db? user=root&password=root&useUnicode=true&characterEncoding=UTF-8";

        String username = "root";
        String password = "root";
        try {
            Class.forName(driverClass);// �������ݿ�����
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }    
        try {
            conn = DriverManager.getConnection(url, username,password);//��������
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("���ݿ�����ʧ�ܣ�");
        }   
        return conn;        
    }
    //�ر���������ݿ��й�����
    protected void finalize()   
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
