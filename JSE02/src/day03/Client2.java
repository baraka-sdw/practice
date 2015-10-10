package day03;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * �ͻ���
 * @author Administrator
 *
 */
public class Client2 {
	//�ͻ����������������ͨ�ŵ�socket
	private Socket socket;
	/**
	 * ��ʼ���ͻ����������
	 * 
	 */
	public Client2() {
		try {
			/**
			 * ʵ����Socket�Ĺ��̾������ӵĹ���
			 * ͨ������Ҫ������������1
			 * 1���ַ�������������IP��ַ
			 * 2������������������Ķ˿ںţ�ServerSocket����ʱ����Ķ˿ڣ�8088��
			 */
			System.out.println("�������ӷ�����...");
			socket=new Socket("localhost",8088);
			System.out.println("�����ӷ�����!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ͻ������ڽ����ķ���
	 */
	@SuppressWarnings("resource")
	public void start() {
		try {
			/**
			 * ����һ���̣߳����ڶ�ȡ�������˷��͹�������Ϣ
			 */
			Runnable handler=new GetMessageFromServerHandler();
			Thread t=new Thread(handler);
			t.start() ;
			/*
			 * �ͻ�������������˷�����Ϣ��ͨ��Socket��ȡ�������֮��д�����ݼ���
			 */
			OutputStream out =socket.getOutputStream();
			/*
			 * ��������˷����ַ��������ǿ��Խ��ֽ���װ��λ�����ַ������PrintWriter
			 */
			OutputStreamWriter osw=new OutputStreamWriter(out,"UTF-8");
			//����һ���ַ�����Ӧ������д��������Ҫ�Զ���ˢ��
			PrintWriter  pw=new PrintWriter(osw,true);
			/**
			 * ����Scanner��������̨������ַ���ͨ��pw���������
			 */
			Scanner sc=new Scanner(System.in);
			while(true){
				String a=sc.nextLine();
				pw.println(a);
			}
		} catch (Exception e) {
		}
	}
	public static void main(String[] args) {
		Client client=new Client();
		client.start();
	}
	/**
	 * ���̵߳��������ÿͻ��˿��Զ�ȡ�������˷��͹�������Ϣ
	 * @author Administrator
	 *
	 */
	class GetMessageFromServerHandler implements Runnable{
		@Override
		public void run() {
			/*
			 * ͨ��Socket��ȡ����������ת��Ϊ�����ַ���������
			 * ��ѭ����ȡ�������˷��͵�ÿһ����Ϣ
			 */
			try {
				InputStream is= socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(is);
				BufferedReader br=new BufferedReader(isr);
				String message=null;
				OutputStream os=socket.getOutputStream();
				OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");
				PrintWriter pw=new PrintWriter(osw,true);

				while((message=br.readLine())!=null){
					System.out.println("��������˵��"+message);
					pw.println(message);
				}
			} catch (Exception e) {
				
			}
		}
		
	}
	
}

