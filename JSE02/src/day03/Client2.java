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
 * 客户端
 * @author Administrator
 *
 */
public class Client2 {
	//客户端用于与服务器端通信的socket
	private Socket socket;
	/**
	 * 初始化客户端相关内容
	 * 
	 */
	public Client2() {
		try {
			/**
			 * 实例化Socket的过程就是连接的过程
			 * 通常我们要传入两个参数1
			 * 1：字符串，服务器的IP地址
			 * 2：整数，服务器申请的端口号（ServerSocket创建时申请的端口，8088）
			 */
			System.out.println("正在连接服务器...");
			socket=new Socket("localhost",8088);
			System.out.println("已连接服务器!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 客户端用于交互的方法
	 */
	@SuppressWarnings("resource")
	public void start() {
		try {
			/**
			 * 创建一个线程，用于读取服务器端发送过来的信息
			 */
			Runnable handler=new GetMessageFromServerHandler();
			Thread t=new Thread(handler);
			t.start() ;
			/*
			 * 客户端想向服务器端发送消息，通过Socket获取输出流，之后写出数据即可
			 */
			OutputStream out =socket.getOutputStream();
			/*
			 * 向服务器端发送字符串，我们可以讲字节流装换位缓冲字符输出流PrintWriter
			 */
			OutputStreamWriter osw=new OutputStreamWriter(out,"UTF-8");
			//发送一个字符串就应当立即写出，所以要自动行刷新
			PrintWriter  pw=new PrintWriter(osw,true);
			/**
			 * 创建Scanner，将控制台输入的字符串通过pw发给服务端
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
	 * 该线程的作用是让客户端可以读取服务器端发送过来的消息
	 * @author Administrator
	 *
	 */
	class GetMessageFromServerHandler implements Runnable{
		@Override
		public void run() {
			/*
			 * 通过Socket获取输入流，在转换为缓冲字符输入流，
			 * 最循环读取服务器端发送的每一行信息
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
					System.out.println("服务器端说："+message);
					pw.println(message);
				}
			} catch (Exception e) {
				
			}
		}
		
	}
	
}

