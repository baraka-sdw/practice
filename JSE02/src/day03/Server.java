package day03;
/*
 * �����
 */
import java.io.BufferedReader;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;


public class Server {
	//������ͻ������ӵ�ServerSocket
	Socket socket;
	private ServerSocket server;
	//������пͻ��˵���������ù㲥��Ϣ
	//�̳߳أ����ڿ��Ʒ��������߳��������������߳�
	private ExecutorService threadPool;
	private List<PrintWriter> allOut;
	/**
	 * ���췽�������ڳ�ʼ����������ص�����
	 */
	public Server() {
		try {
			//��ʼ��ServerSocket
			/**
			 * ��ʼ��ʱҪ�����Ǵ���һ�����������������ʾ�˿ںţ�
			 * �ͻ��˾���ͨ������˿ںţ����ӵ��������˵�
			 */
			server=new ServerSocket(8088);
			
			
			//��ʼ��������еĿͻ���������ļ���
			allOut=new ArrayList<PrintWriter>();
			
			//��ʼ���̳߳�
			threadPool=Executors.newFixedThreadPool(50);//newFixedThreadPool�Ǿ�̬����
			
			
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
	public void start(){
		PrintWriter pw=null;
	  try {
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
	    	  removeOut(pw);
				//�����������
				System.out.println("��ǰ����������"+allOut.size());
	      try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	      }
	
	}
	public static void main(String[] args) {
		Server server=new Server();
		server.start();
	}
	private synchronized void addOut(PrintWriter out){
		allOut.add(out);
	}
	/*
	 * �ӹ�������ɾ�������������
	 */
	private synchronized void removeOut(PrintWriter out){
		allOut.remove(out);
	}
	//��Ҫ�б����㷨�����Ҳ������ϵķ���Ҫ����
	/**
	 * ����������������������ַ������͸����пͻ���
	 * @param message
	 */
	private synchronized void sendMsgToAllClient(String message){
		for (PrintWriter pw:allOut) {
			pw.println(message);
			
		}
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
				//���ÿͻ��˵���������빲����
				addOut(pw);
				//�����������
				System.out.println("��ǰ����������"+allOut.size());
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
//					System.out.println("�ͻ���˵��"+message);
//					//����ȡ������Ϣ���͸��ͻ���
//					pw.println(message);
					//����Ϣת�����㲥�����пͻ���
					sendMsgToAllClient(message);
				}
			} catch (Exception e) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}finally{
				
			}
		}
		
	}
}
