package client;
/*
 * 服务端
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
	//用于与客户端连接的ServerSocket
	Socket socket;
	private ServerSocket server;
	//保存接收来的配对日志
	private File serverLogRec;
	//用于保存客户端发送过来的配对日志，这里缓存为了提高响应效率
	private LinkedBlockingQueue<String> logQueue;
	
	ExecutorService threadPool;
	/**
	 * 构造方法，用于初始化服务器相关的内容
	 */
	public DMSServer() {
		try {
			//初始化ServerSocket
			/**
			 * 初始化时要求我们传入一个整数，这个整数表示端口号，
			 * 客户端就是通过这个端口号，连接到服务器端的
			 */
			 serverLogRec=new File("serverlogrec.txt");

			server=new ServerSocket(8088);
			//初始化线程池
			threadPool=Executors.newFixedThreadPool(50);
			logQueue=new LinkedBlockingQueue<String>();
			
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
	@Test
	public void start(){
		PrintWriter pw=null;
	  try {
		   /*
		    * 启动用于向文件中写入日志的线程
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
				    /**
				     * 将客户端发送过来的每一条配对信息
				     * 保存起来（放入缓冲队列，等待写入文件）
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
	 * 该线程用于保存周期性从缓冲队列中取出日志信息，并写入文件中
	 * @author Administrator
	 *
	 */
	public class WriteLogHandler implements Runnable{

		@Override
		public void run() {
			PrintWriter pw=null;
			 try {
				 //创建文件输出流追加写操作
				 FileOutputStream fos=new FileOutputStream(serverLogRec,true);
				 pw=new PrintWriter(fos);
				/*
				 * 循环从队列中取出每一个日志，并写入文件
				 * 若队列中没有元素了，可以休息一会
				 */
				 while(true){
					 if(logQueue.size()==0){
						 pw.flush();
					    Thread.sleep(5000);
					 }else{
						 //若队列中有日志，就取出来写入文件
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

