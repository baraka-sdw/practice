package client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.lang.ProcessBuilder.Redirect;
import java.net.Socket;
import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import bo.LogDate;
import bo.LogRec;

/*
 * DMS的客户端，用来将wtmpx文件中的日志进行解析，配对，发送至服务端
 */
public class DMSClient {
	private Socket socket;
	//该属性用于描述系统日志文件wtmpx（二进制文件）
	private File logFile;
	//该属性用于保存从wtmpx解析出来的日志（文本文件）
	private File textLogFile;
	//一次从wtmpx中解析的日志条目数
	private File loginLogFile;//该文件用于保存匹配成对的日志文件
	private File logRec;//该文件保存配对成功后的文件
	private int batch;
	//该文件中保存上次读取到的位置，以便继续读取
	private File lastPositionFile;
	/*
	 * 构造方法，初始化相关信息
	 */
	public DMSClient() {
		try {
			//一次读取十条数据
			batch=10;
			//初始化日志文件
			logFile=new File("wtmpx");
			//保存解析出来的日志文件
			textLogFile=new File("log.txt");
			//保存上次读取wtmpx文件的的位置，以便下次继续读取
			lastPositionFile=new File("last_position.dat");
			//初始化保存所有未匹配成功的日志文件
			loginLogFile=new File("login.txt");
			//初始化保存所有配对成功的日志文件
			logRec=new File("logrec.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * 批量的从wtmpx文件中读取日志信息并解析为文本形式写入到log.txt中
	 * 解析完毕返回true
	 * 
	 * 
	 * /**
		 * 实现步骤：
		 * 1:必要判断
		 *   1.1：wtmpx文件要存在，不存在直接返回false
		 *   1.2：判断问题，wtmpx文件是否还有数据可读
		 * 2：解析工作
		 *   2.1：创建RandomAccessFile用于读取wtmpx文件
		 *   2.2：创建缓冲字符输出流向log.txt文件中写
		 *   2.3：循环batch次
		 *   2.4：将raffle的游标移动到一条数据的起始位置
		 *   2.5：按照LogData中定义的每条数据的位置及长度，将每一条
		 *   数据的5项内容读取出来，并存入一个LogData实例中
		 *   2.6：将每个实例存入一个集合中
		 *   2.7：将所有日志文件按行写入log.txt文件
		 **/
	/**
	 * 根据上次读取的位置判断wtmpx文件中是否还有内容
	 * 可读取，若有，并返回开始位置，若没有则返回-1
	 * @return
	 * @throws IOException 
	 */
	 public long hasLogs() throws IOException{
		 /*
		  * 1：判断记录上次读取到的位置的文件是否存在
		  * 1.1：若不存在，这说明没有读取过文件，从头开始即可
		  * 1.2：若存在。读取上次的位置，并判断是否小于等于wtmpx文件的长度，若是说明还有数据可读
		  */
		 long position=0;
		 //1.2
		 if(lastPositionFile.exists()){
			 RandomAccessFile raf=null;
			 try {
				raf=new RandomAccessFile(lastPositionFile, "r");
				position=raf.readLong();
			} finally{
				try {
					if(raf!=null){
						raf.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		 }
		 //判断是否还有内容可读
	 if(position>=logFile.length()){
		 position=-1;
	 }
		 return position;
	 }
	 public void saveLastPosition(long position){
		 RandomAccessFile raf=null;
		 try {
			 raf=new RandomAccessFile(lastPositionFile, "rw");
			 raf.writeLong(position);
	 } catch (IOException e) {
		 e.printStackTrace();
	}finally{
		 if(raf!=null){
			 try{
				 raf.close();
			 }catch(IOException e){
				 
			 }
		 }
	  }
	 }
	public boolean readNextLogs() throws IOException{
		//1.1
		if(!logFile.exists()){
			return false;
		}
		//1.2
		long position=hasLogs();
		if(position<0){
			return false;
		}
		//2 
		RandomAccessFile raf=null;
		PrintWriter pw=null;
		try {
			raf=new RandomAccessFile(logFile, "r");
			pw = new PrintWriter(textLogFile);
			//解析用户名
			raf.seek(position);
			List<LogDate> logs=new ArrayList<LogDate>();
			for(int i=0;i<batch&&raf.getFilePointer()<=logFile.length();i++){
				//先计算当前一条日志在wtmpx文件中的起始位置
				byte[] userData=new byte[LogDate.USER_LENGTH];
				raf.read(userData);
				//unix系统日志中的字符都是按照ISO8859-1编码的
				String user=new String(userData,"iso8859-1").trim();
	            //解析pid
				raf.seek(position+LogDate.PID_OFFSET);
				int pid=raf.readInt();
				//解析type
				raf.seek(position+LogDate.TYPE_OFFSET);
				short type=raf.readShort();
				//解析time
				raf.seek(position+LogDate.TIME_OFFSET);
				int time=raf.readInt();
				//解析host
				byte[] hostData=new byte[LogDate.HOST_LENGTH];
				raf.seek(position+LogDate.HOST_OFFSET);
				raf.read(hostData);
				String host=new String(hostData,"iso8859-1").trim();
				//解析后将5个数据存入一个LogData实例中
				LogDate log=new LogDate(user, pid, type, time, host);
				//将LogData实例存入集合
				logs.add(log);
				//输出每次解析的内容，便于调试
				System.out.println(log.toString());
				//先计算当前一条日志在wtmpx文件中的起始位置
				position=position+LogDate.LOG_LENGTH;
				raf.seek(position);
			}
			//1:将解析后的日志写入textLogFile中
			//2:保存当前读取的位置,以便下次读取
			
			//1
			for(LogDate log : logs){
				pw.println(log);
			}
			
			//2
			saveLastPosition(raf.getFilePointer());
		
		} catch (Exception e) {
		} finally{
			if(raf != null){
				try {
					raf.close();
				} catch (IOException e){}
			}
			if(pw != null){
				pw.close();
			}
		}
		
		return false;
	}
	
	/*
	 * 从给定的文件中读取所有的日志，并存入集合后，将该集合返回
	 * 保存日志的文件
	 * 返回存有所有日志的集合(读取日志，返回集合)
	 */
	public List<LogDate> loadlogDate(File file) {
	    List<LogDate> list=new ArrayList<LogDate>();
	    BufferedReader br=null;
	    try {
	    	br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	    	 String line=null;
	 	     while((line=br.readLine())!=null){
	 	    	 LogDate ld=new LogDate(line);
	 	    	 list.add(ld);
	 	    }
		} catch (Exception e) {
		}finally{
			if(br!=null){
				try {
					br.close();
				} catch (Exception e2) {
				}
			}
		}
	   
		return list;
		
	}
	/**
	 * 根据给定的登入日志与待配对的集合
	 * 找出与之配对的登出日志，并返回配对对象
	 * @param login
	 * @param list
	 * @return 返回配对对象，若返回值为null表示没有找到对应的登出
	 */
	public LogRec matchLogout(LogDate login ,List<LogDate> list){
		//查看参数是否为登入日志
		if(login.getType()!=LogDate.TYPE_LOGIN){
			return null;
		};
		for(LogDate logout: list){
			//查看logout是否为登出日志
			if(logout.getType()!=LogDate.TYPE_LOGOUT){
				continue;
			};
			//查看用户名是否一致
			if(!login.getUser().equals(logout.getUser())){
				continue;
			};
			//查看pid是否一致
			if(login.getPid()!=(logout.getPid())){
				continue;
			};
			//查看host是否一致
			if(!login.getHost().equals(logout.getHost())){
				continue;
			};
			//都匹配,创建LogRec对象并返回
			LogRec logRec=new LogRec(login, logout);
			return logRec;
		}
		//for循环退出了，说明没找到对应的登出（配对失败）
		return null;
		
	}
	/*
	 * 第二步 匹配日志
	 */
	@Test
	public boolean matchLogs() {
		/**
		 * 首先判断：
		 * 1：检查Logs.txt文件是否存在（解析后的文件）
		 * 没有的话没法配对
		 * 将logs.txt文件中每行日志读取回来，并转换为若干个LogData实例，
		 * 并存入一个集合中等待配对
		 * 查看上次是否存在没有匹配成对的日志，有则将这些日志转化为LogData对象，
		 * 并也放入集合中等待配对
		 * 
		 * 2:遍历集合，找出所有可以配对的日志（登入与登出）
		 * 配对条件：用户名，pid，Host都要相同，然后Type一个是7，一个是8
		 * 将一组配对的日志存对象中，并将LogRec对象存入一个集合中
		 * 
		 * 3:将所有没配对成功的日志存入另一个集合中
		 * 将配对成功的集合中的所有日志写入一个文件,logrec.txt，
		 * 将没有配对成功的日志写入另一个文件Login.txt
		 * 
		 * 将第一步生成的logs.txt文件删除
		 * 
		 */
		 if(!textLogFile.exists()){
		    	System.out.println("log.txt不存在");
		    	return false;
		    }
		//从文件取出解析出的10条数据
		//创建用于保存待配对日志的集合
		List<LogDate> ld=new ArrayList<LogDate>();
		/*
		 * 创建用于保存配对成功的日志集合
		 */
		List<LogRec> lr=new ArrayList<LogRec>();
		//将log.txt文件中所有的元素添加到list中
		ld.addAll(loadlogDate(textLogFile));
	         
	    //检查上次保存的文件未匹配成功的日志文件是否存在
	        
	    if(loginLogFile.exists()){
	    	ld.addAll(loadlogDate(loginLogFile));
	    }
	    //再创建一个集合，用于保存所有配对没成功的日志
	    List<LogDate> loginlist=new ArrayList<LogDate>();
	    //循环待配对的日志，进行配对工作
	    for (LogDate login :ld){
	    	//准备配对
	    	if(login.getType()!=LogDate.TYPE_LOGIN){
	    		continue;
	    	}
	    	LogRec logRec=matchLogout(login, ld);
	    	if(logRec==null){
	    		//没有配对成功
	    		loginlist.add(login);
	    	}else{
	    		//配对成功
	    		lr.add(logRec);
	    	}
	    }
	    //分别将成功与失败的日志写入不同的文件
	   try {
		   //写入成功配对的日志
		   saveList(logRec,lr);
		   //写入没有成功配对的日志
		   saveList(loginLogFile, loginlist);
		   //将第一步解析出来的保存的十条日志文件删除
//		   textLogFile.delete();
			return true;
	} catch (Exception e) {
	}
		return false;
		
	}
	/**
	 * 将给定的集合中的元素顺序调用tostring，并将返回后的字符串按行写入给定文件中
	 * @param file 文件名
	 * @param list 集合名
	 */
	public void saveList(File file, List list) {
		PrintWriter pw=null;
		try {
			pw=new PrintWriter(file);
			for(Object o:list){	
				pw.println(o);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			    if(pw!=null){
		        pw.close();
			}
		}
		
	}
	/**
	 * 第三步：
	 * 将配对的数据发送至服务器端保存
	 * @return
	 */
	/**
	 * @return
	 */
	public boolean sendLogRecToServer(){
		 /**
		  * 1：必要的判断
		  *     1.1：检查保存配对成功的日志文件是否存在
		  * 2:将logrec.txt文件中每一行数据发送给服务器端
		  *    2.1：创建socket与服务器端连接
		  *    2.2：通过Socket获取缓冲输出流，并包装为缓冲字符输出流，
		  *    用于将数据发送给服务器端
		  *    2.3：创建读取logrec.txt文件的缓冲字符输入流
		  *    用于按行读取每一对配对日志
		  *    2.4：顺序读取文件中的每一行日志，并发送给服务器端
		  * 3：断开连接，释放资源
		  */
		if(!logRec.exists()){
			System.out.println("文件不存在");
			return false;
		}
		socket=null;//用于连接服务器
		BufferedReader br=null;//用于读取配对日志
		try {
			socket=new Socket("localhost",8088);
			OutputStream out=socket.getOutputStream();
			OutputStreamWriter osw=new OutputStreamWriter(out,"utf-8");
			PrintWriter pw=new PrintWriter(osw);
			br=new BufferedReader(new InputStreamReader(new FileInputStream(logRec)));
			String line=null;
			while((line=br.readLine())!=null){
				pw.println(line);
			}
			pw.flush();//将缓冲区的数据全部写出
		} catch (Exception e) {
		}finally{
			if(socket!=null){
				try {
					socket.close();
				} catch (Exception e2) {
					
				}
			}
			if(br!=null){
				try {
					br.close();
				} catch (Exception e2) {
				}
			}
		}
		return false;
		//2
	
	}
	//客户端开始工作的方法
	public void start() throws IOException{
		/*
		 * 完成收集数据的工作，三步
		 * 1：从wtmpx文件中取出一组数据（batch决定数量），并解析成字符串后保存
		 * 2：将解析后的日志按照登入登出配对
		 * 3：将配对的数据发送到服务器
		 */
		//1
		readNextLogs();
		//2
		matchLogs();
		//3
        sendLogRecToServer();		
	}

	public static void main(String[] args) throws IOException {
		DMSClient dc=new DMSClient();
		dc.start();
	}
}
