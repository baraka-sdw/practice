package util;

import util.ConfigUtil;

/**
 * Factory�����������ļ������ã�������
 * ������һ��ʵ����
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
