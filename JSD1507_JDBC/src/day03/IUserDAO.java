package day03;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO接口
 * 定义对数据库的相关操作
 * @author Administrator
 *
 */
public interface IUserDAO {
	/**
	 * 根据ID获取对应的user记录
	 * @param id
	 * @return
	 */
	public User findById(int id)throws SQLException;
	/**
	 * 查询所有的用户记录库
	 * @return
	 */
	public List<User> findAll()throws SQLException;
	
	/**
	 * 保存给定的用户记录
	 * @param user
	 * @return
	 */
	public User save(User user)throws SQLException;
	/**
	 * 修改给定的用户记录
	 * @param user
	 * @return
	 */
	public User update(User user)throws SQLException;
	
	/**
	 * 删除给定的用户记录
	 * @param user
	 * @return
	 */
	public boolean delete(User user)throws SQLException;
}




