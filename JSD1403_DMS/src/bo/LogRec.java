package bo;
/*
 * ����־�ļ����ڱ�ʾһ����Ե���־
 * ��Ե���־����������־
 * һ�����룬һ���ǳ�
 */
public class LogRec {
	private LogDate login;
	private LogDate logout;
	public LogRec(LogDate login, LogDate logout) {
		this.login = login;
		this.logout = logout;
	}
	@Override
	public String toString() {
		return login+"|"+logout;
	}
	

}
