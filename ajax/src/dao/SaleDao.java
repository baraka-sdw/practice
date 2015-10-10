package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Sale;

public class SaleDao {
	public List<Sale> findAll() throws Exception{
		List<Sale> sales=new ArrayList<Sale>();
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getConnection();
			ps=conn.prepareStatement("select * from (select rownum r,a.* from(select * from t_sale order by qty  desc)a)c where c.r<4");
		    rs=ps.executeQuery();
		    while(rs.next()){
		    	Sale s=new Sale(rs.getInt("id"),rs.getString("prodName"),rs.getInt("qty"));
		    	sales.add(s);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn);
		}
		return sales;
	}
}
