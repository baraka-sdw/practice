package dao;

import java.util.List;

import entity.Computer;

public interface ComputerDAO {
	public void save(Computer c) throws Exception;
	public Computer findById(long id) throws Exception;
	public List<Computer> findAll() throws Exception;
}
