package day03;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO�ӿ�
 * ��������ݿ����ز���
 * @author Administrator
 *
 */
public interface IUserDAO {
	/**
	 * ����ID��ȡ��Ӧ��user��¼
	 * @param id
	 * @return
	 */
	public User findById(int id)throws SQLException;
	/**
	 * ��ѯ���е��û���¼��
	 * @return
	 */
	public List<User> findAll()throws SQLException;
	
	/**
	 * ����������û���¼
	 * @param user
	 * @return
	 */
	public User save(User user)throws SQLException;
	/**
	 * �޸ĸ������û���¼
	 * @param user
	 * @return
	 */
	public User update(User user)throws SQLException;
	
	/**
	 * ɾ���������û���¼
	 * @param user
	 * @return
	 */
	public boolean delete(User user)throws SQLException;
}




