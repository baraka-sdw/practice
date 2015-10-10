package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读属性文件的一个工具 .properties文件
 * 
 * @author teacher
 * 
 */
public class ConfigUtil {
	private static Properties props = new Properties();
	static {
		// load(InputStream)
		// 将InputStream中的数据放到Properties对象里面。
		// ConfigUtil.class.getClassLoader():
		// 得到加载ConfigUtil类的ClassLoader
		// getResourceAsStream():搜索classpath,将找到
		// 的文件转化成一个InputStream。
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
