package day03;
/*
 * 服务端
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
	//用于与客户端连接的ServerSocket
	Socket socket;
	private ServerSocket server;
	//存放所有客户端的输出流，用广播信息
	//线程池，用于控制服务器端线程数量，并重用线程
	private ExecutorService threadPool;
	private List<PrintWriter> allOut;
	/**
	 * 构造方法，用于初始化服务器相关的内容
	 */
	public Server() {
		try {
			//初始化ServerSocket
			/**
			 * 初始化时要求我们传入一个整数，这个整数表示端口号，
			 * 客户端就是通过这个端口号，连接到服务器端的
			 */
			server=new ServerSocket(8088);
			
			
			//初始化存放所有的客户端输出流的集合
			allOut=new ArrayList<PrintWriter>();
			
			//初始化线程池
			threadPool=Executors.newFixedThreadPool(50);//newFixedThreadPool是静态方法
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 将给定的输出流存入共享集合中
	 */
	
	
	
	
	/**
	 *服务器开始工作的方法
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
		   * 该方法是一个阻塞方法，用于等待客户端的链接
		   * 一旦一个客户端连接上，该方法会返回与客户端通信的Socket
		   */
		  //死循环的目的是一直监听不同客户端的连接
		while(true){
			  System.out.println("等待客户端连接...");
			  socket=server.accept();
			  System.out.println("一个客户端连接了.");
			  /**
				 * 当一个客户端连接后，启动一个线程，将客户端的Socket传入，使该线程与该客户端通信
				 */
				Runnable clientHander=new ClierntHander(socket);
//				Thread t=new Thread(clientHander);
//				t.start();
				//将任务指派给线程池
				threadPool.execute(clientHander);
				

		}
		
	} catch (Exception e) {
		System.out.println("客户端下线了");
		/**
		 * 不同分别关闭输入流和输出流
		 * 关闭Socket即可，因为这两个流都是从Socket
		 * 获取的，就好比打电话，我们最终挂电话就自然断开了麦克风和听筒一样
		 */
	      }finally{
	    	  /**
	    	   * linux的客户端若断开连接，服务器端会读取到null
	    	   * windows的客户端断开连接，服务端会抛出异常
	    	   * 所以finally是我们最后做处理的最佳地点
	    	   */
	    	  System.out.println("客户端下线了");
	    	  //当客户端断开后，将其输出流从共享集合中删除
	    	  removeOut(pw);
				//输出在线人数
				System.out.println("当前在线人数："+allOut.size());
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
	 * 从共享集合中删除给定的输出流
	 */
	private synchronized void removeOut(PrintWriter out){
		allOut.remove(out);
	}
	//还要有遍历算法，并且操作集合的方法要互斥
	/**
	 * 遍历所有输出流将给定的字符串发送给所有客户端
	 * @param message
	 */
	private synchronized void sendMsgToAllClient(String message){
		for (PrintWriter pw:allOut) {
			pw.println(message);
			
		}
	}
	
	
	/**
	 * 该线程的作用是与给定的客户端Socket进行通信
	 * @author Administrator
	 *
	 */
	class ClierntHander implements Runnable{
		//当前线程用于交流的指定客户端的Socket
		private Socket socket;
		/**
		 * 创建线程体时将交互的Socket传入
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
				 * 通过Socket获取输出流，用于将信息发送给客户端
				 */
				OutputStream os=socket.getOutputStream();
				OutputStreamWriter osw=new OutputStreamWriter(os,"UTF-8");
				pw=new PrintWriter(osw,true);
				//将该客户端的输出流存入共享集合
				addOut(pw);
				//输出在线人数
				System.out.println("当前在线人数："+allOut.size());
				/**
				 * 通过连接上的客户端Socket获取输入流
				 * 来读取客户端发过来的信息
				 */
				InputStream is=socket.getInputStream();
				InputStreamReader isr=new InputStreamReader(is,"UTF-8");
				//包装为缓冲字符输入流，可以按行读取字符串
				BufferedReader br=new BufferedReader(isr);
				String message=null;
				while ((message=br.readLine())!=null) {
//					System.out.println("客户端说："+message);
//					//将读取到的信息发送给客户端
//					pw.println(message);
					//将消息转发给广播给所有客户端
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
