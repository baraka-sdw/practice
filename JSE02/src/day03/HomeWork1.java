package day03;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 线程第5题
 * 
 * @author Administrator
 * 
 */
public class HomeWork1{
	public static void main(String[] args) {
		/*
		 * 1:创建一个线程，用于计时SimpleDateFormat
		 * 2：线程中计时
		 *    2.1：创建、
		 *    2.2：循环以下操作2.3,2.4
		 *    2.3：创建一个Date实例，表示系统时间
		 *    2.4：使用SimpleDateFormat将Date转换为字符串后输出
		 *    2.5：阻塞线程5000毫秒
		 * 3：设置线程为守护线程（后台线程）
		 * 4：将线程启动
		 * 5:为了保证守护线程可以运行一段时间，我们阻塞main方法的线程10秒钟
		 */
		Thread t=new Thread(){
			@Override
			public void run() {
				SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
				while(true){
					Date nowDate=new Date();
					String date=sdf.format(nowDate);
					System.out.println(date);
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						
					}
					
				}
			
			
		   }
			
		};
		t.setDaemon(true);
		t.start();
		try {
			Thread.sleep(15000);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}