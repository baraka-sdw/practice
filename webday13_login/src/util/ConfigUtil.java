package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * �������ļ���һ������ .properties�ļ�
 * 
 * @author teacher
 * 
 */
public class ConfigUtil {
	private static Properties props = new Properties();
	static {
		// load(InputStream)
		// ��InputStream�е����ݷŵ�Properties�������档
		// ConfigUtil.class.getClassLoader():
		// �õ�����ConfigUtil���ClassLoader
		// getResourceAsStream():����classpath,���ҵ�
		// ���ļ�ת����һ��InputStream��
		InputStream ips = ConfigUtil.class.getClassLoader()
				.getResourceAsStream("daoconfig.properties");
		try {
			props.load(ips);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return props.getProperty(key);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getProperty("Hello"));
	}

}
