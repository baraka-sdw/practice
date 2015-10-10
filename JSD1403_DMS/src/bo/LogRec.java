package bo;
/*
 * 该日志文件用于表示一个配对的日志
 * 配对的日志含有两条日志
 * 一个登入，一个登出
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
