package day03;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sleep����
 * @author Administrator
 *
 */
public class TestThreadDemo8 {
	 static Date date;
	 static SimpleDateFormat sdf;
	public static void main(String[] args) {
		//ʵ��һ�����ӱ���
		/**
		 * ÿ�����һ�µ�ǰʱ��--> 
		 * 1������SimpleDateFormatָ��ʱ���ʽ
		 * 2��������ǰϵͳʱ���Ӧ��Date����
		 * 3:ͨ��SimpleDateFormat��format������Dateת��Ϊ�ַ���
		 * 4����ʵ�ֵ��ӱ��ܣ���ѭ��ÿ��һ��ִ��һ��2,3����
		 */
		  sdf=new SimpleDateFormat("HH:mm:ss");
	    Thread t1 =new Thread(){
	    	public void run() {
	    		while(true){
	    		date=new Date();
	    		String a=sdf.format(date);
	    		System.out.println(a);
	    		
				try {
					sleep(1000,10000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			  }
	    	}
	    	
	    };
	    t1.start();

	}

	

}
