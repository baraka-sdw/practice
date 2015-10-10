package bo;
/**
 * LogDate用于描述unix系统日志文件wtmpx中的内容
 * 每一个实例表示一条日志
 * @author Administrator
 *
 */
public class LogDate {
	/*
	 * 一条日志文件wtmpax文件中占用的字节量
	 */
	public static final int LOG_LENGTH=372;
	//每条日志中记录的用户名
	private String user;
	//用户名在日志文件中的偏移位置
	public static final int USER_OFFSET=0;
	//用户名在日志文件中所占用的字节量
	public static final int USER_LENGTH=32;
	//每条日志中记录的进程pid
	private int pid;
	//PID在日志文件中的偏移量
	public static final int PID_OFFSET=68;
	//每条日志记录中的类型
	private short type;
	//type在日志文件中的偏移量
	public static final int TYPE_OFFSET=72;
	//登入类型取值
	public static final int TYPE_LOGIN=7;
	//登出类型取值
	public static final int TYPE_LOGOUT=8;
	//每条日志记录中的时间
	private int time;
	//time在日志文件中的偏移量
	public static final int TIME_OFFSET=80;
	//time在日志文件中所占用的字节量
	public static final int TIME_LENGTH=4;
	//每条日志记录中的host
	private String host;
	//host在日志文件中的偏移量
	public static final int HOST_OFFSET=114;
	//host在日志文件中所占用的字节量
	public static final int HOST_LENGTH=258;
	public LogDate(String user, int pid, short type, int time, String host) {
		this.user = user;
		this.pid = pid;
		this.type = type;
		this.time = time;
		this.host = host;
	}
	/**
	 * 将一个格式为tostring方法返回的日志字符串转换为一个对应的LogDate实例
	 * @param log  描述该日志的字符串格式应该是tostring方法返回的样子
	 */
	public LogDate(String log){
		//1根据“，”拆分字符串
		String[] logs=log.split(",");
		this.user=logs[0];
		this.pid=Integer.parseInt(logs[1]);
		this.type=Short .parseShort(logs[2]);
		this.time=Integer.parseInt(logs[3]);
		this.host=logs[4];

	  
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public short getType() {
		return type;
	}
	public void setType(short type) {
		this.type = type;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	@Override
	public String toString() {
		return user+","+pid+","+type+","+time+","+host;
	}
	

}
