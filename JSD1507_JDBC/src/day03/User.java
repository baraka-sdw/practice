package day03;
/**
 * user��һ��ʵ����
 * ��ʾ���ݿ�ı�user
 * ͨ��ʵ��������ֺ����ݿ��б��������ͬ
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
