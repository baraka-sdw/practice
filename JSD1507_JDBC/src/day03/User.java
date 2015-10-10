package day03;
/**
 * user是一个实体类
 * 表示数据库的表user
 * 通常实体类的名字和数据库中表的名字相同
 * @author Administrator
 *
 */
public class User {
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	private int id;
	private String username;
	private String password;

	public User() {
		super();
	}

	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static void main(String[] args) {
             
	}

}
