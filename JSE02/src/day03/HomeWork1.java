package day03;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * �̵߳�5��
 * 
 * @author Administrator
 * 
 */
public class HomeWork1{
	public static void main(String[] args) {
		/*
		 * 1:����һ���̣߳����ڼ�ʱSimpleDateFormat
		 * 2���߳��м�ʱ
		 *    2.1��������
		 *    2.2��ѭ�����²���2.3,2.4
		 *    2.3������һ��Dateʵ������ʾϵͳʱ��
		 *    2.4��ʹ��SimpleDateFormat��Dateת��Ϊ�ַ��������
		 *    2.5�������߳�5000����
		 * 3�������߳�Ϊ�ػ��̣߳���̨�̣߳�
		 * 4�����߳�����
		 * 5:Ϊ�˱�֤�ػ��߳̿�������һ��ʱ�䣬��������main�������߳�10����
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