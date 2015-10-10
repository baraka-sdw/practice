package dao;

import entity.User;

public interface UserDAO {
	public void save(User user) throws Exception;

	public User findByUsername(String username) throws Exception;
}
