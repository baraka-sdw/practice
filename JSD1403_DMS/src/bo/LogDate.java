package bo;
/**
 * LogDate��������unixϵͳ��־�ļ�wtmpx�е�����
 * ÿһ��ʵ����ʾһ����־
 * @author Administrator
 *
 */
public class LogDate {
	/*
	 * һ����־�ļ�wtmpax�ļ���ռ�õ��ֽ���
	 */
	public static final int LOG_LENGTH=372;
	//ÿ����־�м�¼���û���
	private String user;
	//�û�������־�ļ��е�ƫ��λ��
	public static final int USER_OFFSET=0;
	//�û�������־�ļ�����ռ�õ��ֽ���
	public static final int USER_LENGTH=32;
	//ÿ����־�м�¼�Ľ���pid
	private int pid;
	//PID����־�ļ��е�ƫ����
	public static final int PID_OFFSET=68;
	//ÿ����־��¼�е�����
	private short type;
	//type����־�ļ��е�ƫ����
	public static final int TYPE_OFFSET=72;
	//��������ȡֵ
	public static final int TYPE_LOGIN=7;
	//�ǳ�����ȡֵ
	public static final int TYPE_LOGOUT=8;
	//ÿ����־��¼�е�ʱ��
	private int time;
	//time����־�ļ��е�ƫ����
	public static final int TIME_OFFSET=80;
	//time����־�ļ�����ռ�õ��ֽ���
	public static final int TIME_LENGTH=4;
	//ÿ����־��¼�е�host
	private String host;
	//host����־�ļ��е�ƫ����
	public static final int HOST_OFFSET=114;
	//host����־�ļ�����ռ�õ��ֽ���
	public static final int HOST_LENGTH=258;
	public LogDate(String user, int pid, short type, int time, String host) {
		this.user = user;
		this.pid = pid;
		this.type = type;
		this.time = time;
		this.host = host;
	}
	/**
	 * ��һ����ʽΪtostring�������ص���־�ַ���ת��Ϊһ����Ӧ��LogDateʵ��
	 * @param log  ��������־���ַ�����ʽӦ����tostring�������ص�����
	 */
	public LogDate(String log){
		//1���ݡ���������ַ���
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
