package day03;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sleep阻塞
 * @author Administrator
 *
 */
public class TestThreadDemo8 {
	 static Date date;
	 static SimpleDateFormat sdf;
	public static void main(String[] args) {
		//实现一个电子表功能
		/**
		 * 每秒输出一下当前时间--> 
		 * 1：创建SimpleDateFormat指定时间格式
		 * 2：创建当前系统时间对应的Date对象
		 * 3:通过SimpleDateFormat的format方法将Date转换为字符串
		 * 4：想实现电子表功能，就循环每隔一秒执行一次2,3步骤
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
