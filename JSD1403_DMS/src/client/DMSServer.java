package client;
/*
 * �����
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;


public class DMSServer {
	//������ͻ������ӵ�ServerSocket
	Socket socket;
	private ServerSocket server;
	//����������������־
	private File serverLogRec;
	//���ڱ���ͻ��˷��͹����������־�����ﻺ��Ϊ�������ӦЧ��
	private LinkedBlockingQueue<String> logQueue;
	
	ExecutorService threadPool;
	/**
	 * ���췽�������ڳ�ʼ����������ص�����
	 */
	public DMSServer() {
		try {
			//��ʼ��ServerSocket
			/**
			 * ��ʼ��ʱҪ�����Ǵ���һ�����������������ʾ�˿ںţ�
			 * �ͻ��˾���ͨ������˿ںţ����ӵ��������˵�
			 */
			 serverLogRec=new File("serverlogrec.txt");

			server=new ServerSocket(8088);
			//��ʼ���̳߳�
			threadPool=Executors.newFixedThreadPool(50);
			logQueue=new LinkedBlockingQueue<String>();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * ����������������빲������
	 */
	/**
	 *��������ʼ�����ķ���
	 */
	@Test
	public void start(){
		PrintWriter pw=null;
	  try {
		   /*
		    * �����������ļ���д����־���߳�
		    */
		  WriteLogHandler wlh=new WriteLogHandler();
		  Thread t=new Thread(wlh);
		  t.start();
//		  OutputStream out =socket.getOutputStream();
//			OutputStreamWriter osw=new OutputStreamWriter(out,"UTF-8");
//			pw=new PrintWriter(osw,true);
//			Scanner sc=new Scanner(System.in);
		  /**
		   * Socket accept()
		   * �÷�����һ���������������ڵȴ��ͻ��˵�����
		   * һ��һ���ͻ��������ϣ��÷����᷵����ͻ���ͨ�ŵ�Socket
		   */
		  //��ѭ����Ŀ����һֱ������ͬ�ͻ��˵�����
		while(true){
			  System.out.println("�ȴ��ͻ�������...");
			  socket=server.accept();
			  System.out.println("һ���ͻ���������.");
			  /**
				 * ��һ���ͻ������Ӻ�����һ���̣߳����ͻ��˵�Socket���룬ʹ���߳���ÿͻ���ͨ��
				 */
				Runnable clientHander=new ClierntHander(socket);
//				Thread t=new Thread(clientHander);
//				t.start();
				//������ָ�ɸ��̳߳�
				threadPool.execute(clientHander);
		}
		
	} catch (Exception e) {
		System.out.println("�ͻ���������");
		/**
		 * ��ͬ�ֱ�ر��������������
		 * �ر�Socket���ɣ���Ϊ�����������Ǵ�Socket
		 * ��ȡ�ģ��ͺñȴ�绰���������չҵ绰����Ȼ�Ͽ�����˷����Ͳһ��
		 */
	      }finally{
	    	  /**
	    	   * linux�Ŀͻ������Ͽ����ӣ��������˻��ȡ��null
	    	   * windows�Ŀͻ��˶Ͽ����ӣ�����˻��׳��쳣
	    	   * ����finally������������������ѵص�
	    	   */
	    	  System.out.println("�ͻ���������");
	    	  //���ͻ��˶Ͽ��󣬽���������ӹ�������ɾ��
	      try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	      }
	
	}
	public static void main(String[] args) {
		DMSServer server=new DMSServer();
		server.start();
	}
	/**
	 * ���̵߳�������������Ŀͻ���Socket����ͨ��
	 * @author Administrator
	 *
	 */
	class ClierntHander implements Runnable{
		//��ǰ�߳����ڽ�����ָ���ͻ��˵�Socket
		private Socket socket;
		/**
		 * �����߳���ʱ��������Socket����
		 * @param socket
		 */
		public ClierntHander(Socket socket) {
			this.socket = socket;
		}
		@Override
		public void run() {
			PrintWriter pw=null;
			try {
				/*
				 * ͨ��Socket��ȡ����������ڽ���Ϣ���͸��ͻ���
				 */
				OutputStream os=socket.getOutputStream();
				OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");
				pw=new PrintWriter(osw,true);
				/**
				 * ͨ�������ϵĿͻ���Socket��ȡ������
				 * ����ȡ�ͻ��˷���������Ϣ
				 */
				InputStream is=socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(is,"UTF-8");
				//��װΪ�����ַ������������԰��ж�ȡ�ַ���
				BufferedReader br=new BufferedReader(isr);
				String message=null;
				while ((message=br.readLine())!=null) {
				    /**
				     * ���ͻ��˷��͹�����ÿһ�������Ϣ
				     * �������������뻺����У��ȴ�д���ļ���
				     */
					logQueue.offer(message,5,TimeUnit.SECONDS);
				}
			} catch (Exception e) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}
	/**
	 * ���߳����ڱ��������Դӻ��������ȡ����־��Ϣ����д���ļ���
	 * @author Administrator
	 *
	 */
	public class WriteLogHandler implements Runnable{

		@Override
		public void run() {
			PrintWriter pw=null;
			 try {
				 //�����ļ������׷��д����
				 FileOutputStream fos=new FileOutputStream(serverLogRec,true);
				 pw=new PrintWriter(fos);
				/*
				 * ѭ���Ӷ�����ȡ��ÿһ����־����д���ļ�
				 * ��������û��Ԫ���ˣ�������Ϣһ��
				 */
				 while(true){
					 if(logQueue.size()==0){
						 pw.flush();
					    Thread.sleep(5000);
					 }else{
						 //������������־����ȡ����д���ļ�
						 pw.println(logQueue.poll());
					 }
				 }
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(pw!=null){
					pw.close();
				}
			}
		}

	}
 
}

