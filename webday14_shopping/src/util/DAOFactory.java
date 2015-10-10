package util;

import util.ConfigUtil;

/**
 * Factory类依据属性文件的配置，来决定
 * 创建哪一个实例。
 * @author teacher
 *
 */
public class DAOFactory {
	
	public static Object getInstance(Class c){
		//pk1.Student
		// Student.class.getSimpleName() :  Student
		String interfacename = c.getSimpleName();
		String classname = ConfigUtil.getProperty(interfacename);
		Object obj = null;
		try {
			obj = Class.forName(classname).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return obj;
	}
}
