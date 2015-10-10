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
 * DMS�Ŀͻ��ˣ�������wtmpx�ļ��е���־���н�������ԣ������������
 */
public class DMSClient {
	private Socket socket;
	//��������������ϵͳ��־�ļ�wtmpx���������ļ���
	private File logFile;
	//���������ڱ����wtmpx������������־���ı��ļ���
	private File textLogFile;
	//һ�δ�wtmpx�н�������־��Ŀ��
	private File loginLogFile;//���ļ����ڱ���ƥ��ɶԵ���־�ļ�
	private File logRec;//���ļ�������Գɹ�����ļ�
	private int batch;
	//���ļ��б����ϴζ�ȡ����λ�ã��Ա������ȡ
	private File lastPositionFile;
	/*
	 * ���췽������ʼ�������Ϣ
	 */
	public DMSClient() {
		try {
			//һ�ζ�ȡʮ������
			batch=10;
			//��ʼ����־�ļ�
			logFile=new File("wtmpx");
			//���������������־�ļ�
			textLogFile=new File("log.txt");
			//�����ϴζ�ȡwtmpx�ļ��ĵ�λ�ã��Ա��´μ�����ȡ
			lastPositionFile=new File("last_position.dat");
			//��ʼ����������δƥ��ɹ�����־�ļ�
			loginLogFile=new File("login.txt");
			//��ʼ������������Գɹ�����־�ļ�
			logRec=new File("logrec.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	 * �����Ĵ�wtmpx�ļ��ж�ȡ��־��Ϣ������Ϊ�ı���ʽд�뵽log.txt��
	 * ������Ϸ���true
	 * 
	 * 
	 * /**
		 * ʵ�ֲ��裺
		 * 1:��Ҫ�ж�
		 *   1.1��wtmpx�ļ�Ҫ���ڣ�������ֱ�ӷ���false
		 *   1.2���ж����⣬wtmpx�ļ��Ƿ������ݿɶ�
		 * 2����������
		 *   2.1������RandomAccessFile���ڶ�ȡwtmpx�ļ�
		 *   2.2�����������ַ��������log.txt�ļ���д
		 *   2.3��ѭ��batch��
		 *   2.4����raffle���α��ƶ���һ�����ݵ���ʼλ��
		 *   2.5������LogData�ж����ÿ�����ݵ�λ�ü����ȣ���ÿһ��
		 *   ���ݵ�5�����ݶ�ȡ������������һ��LogDataʵ����
		 *   2.6����ÿ��ʵ������һ��������
		 *   2.7����������־�ļ�����д��log.txt�ļ�
		 **/
	/**
	 * �����ϴζ�ȡ��λ���ж�wtmpx�ļ����Ƿ�������
	 * �ɶ�ȡ�����У������ؿ�ʼλ�ã���û���򷵻�-1
	 * @return
	 * @throws IOException 
	 */
	 public long hasLogs() throws IOException{
		 /*
		  * 1���жϼ�¼�ϴζ�ȡ����λ�õ��ļ��Ƿ����
		  * 1.1���������ڣ���˵��û�ж�ȡ���ļ�����ͷ��ʼ����
		  * 1.2�������ڡ���ȡ�ϴε�λ�ã����ж��Ƿ�С�ڵ���wtmpx�ļ��ĳ��ȣ�����˵���������ݿɶ�
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
		 //�ж��Ƿ������ݿɶ�
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
			//�����û���
			raf.seek(position);
			List<LogDate> logs=new ArrayList<LogDate>();
			for(int i=0;i<batch&&raf.getFilePointer()<=logFile.length();i++){
				//�ȼ��㵱ǰһ����־��wtmpx�ļ��е���ʼλ��
				byte[] userData=new byte[LogDate.USER_LENGTH];
				raf.read(userData);
				//unixϵͳ��־�е��ַ����ǰ���ISO8859-1�����
				String user=new String(userData,"iso8859-1").trim();
	            //����pid
				raf.seek(position+LogDate.PID_OFFSET);
				int pid=raf.readInt();
				//����type
				raf.seek(position+LogDate.TYPE_OFFSET);
				short type=raf.readShort();
				//����time
				raf.seek(position+LogDate.TIME_OFFSET);
				int time=raf.readInt();
				//����host
				byte[] hostData=new byte[LogDate.HOST_LENGTH];
				raf.seek(position+LogDate.HOST_OFFSET);
				raf.read(hostData);
				String host=new String(hostData,"iso8859-1").trim();
				//������5�����ݴ���һ��LogDataʵ����
				LogDate log=new LogDate(user, pid, type, time, host);
				//��LogDataʵ�����뼯��
				logs.add(log);
				//���ÿ�ν��������ݣ����ڵ���
				System.out.println(log.toString());
				//�ȼ��㵱ǰһ����־��wtmpx�ļ��е���ʼλ��
				position=position+LogDate.LOG_LENGTH;
				raf.seek(position);
			}
			//1:�����������־д��textLogFile��
			//2:���浱ǰ��ȡ��λ��,�Ա��´ζ�ȡ
			
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
	 * �Ӹ������ļ��ж�ȡ���е���־�������뼯�Ϻ󣬽��ü��Ϸ���
	 * ������־���ļ�
	 * ���ش���������־�ļ���(��ȡ��־�����ؼ���)
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
	 * ���ݸ����ĵ�����־�����Եļ���
	 * �ҳ���֮��Եĵǳ���־����������Զ���
	 * @param login
	 * @param list
	 * @return ������Զ���������ֵΪnull��ʾû���ҵ���Ӧ�ĵǳ�
	 */
	public LogRec matchLogout(LogDate login ,List<LogDate> list){
		//�鿴�����Ƿ�Ϊ������־
		if(login.getType()!=LogDate.TYPE_LOGIN){
			return null;
		};
		for(LogDate logout: list){
			//�鿴logout�Ƿ�Ϊ�ǳ���־
			if(logout.getType()!=LogDate.TYPE_LOGOUT){
				continue;
			};
			//�鿴�û����Ƿ�һ��
			if(!login.getUser().equals(logout.getUser())){
				continue;
			};
			//�鿴pid�Ƿ�һ��
			if(login.getPid()!=(logout.getPid())){
				continue;
			};
			//�鿴host�Ƿ�һ��
			if(!login.getHost().equals(logout.getHost())){
				continue;
			};
			//��ƥ��,����LogRec���󲢷���
			LogRec logRec=new LogRec(login, logout);
			return logRec;
		}
		//forѭ���˳��ˣ�˵��û�ҵ���Ӧ�ĵǳ������ʧ�ܣ�
		return null;
		
	}
	/*
	 * �ڶ��� ƥ����־
	 */
	@Test
	public boolean matchLogs() {
		/**
		 * �����жϣ�
		 * 1�����Logs.txt�ļ��Ƿ���ڣ���������ļ���
		 * û�еĻ�û�����
		 * ��logs.txt�ļ���ÿ����־��ȡ��������ת��Ϊ���ɸ�LogDataʵ����
		 * ������һ�������еȴ����
		 * �鿴�ϴ��Ƿ����û��ƥ��ɶԵ���־��������Щ��־ת��ΪLogData����
		 * ��Ҳ���뼯���еȴ����
		 * 
		 * 2:�������ϣ��ҳ����п�����Ե���־��������ǳ���
		 * ����������û�����pid��Host��Ҫ��ͬ��Ȼ��Typeһ����7��һ����8
		 * ��һ����Ե���־������У�����LogRec�������һ��������
		 * 
		 * 3:������û��Գɹ�����־������һ��������
		 * ����Գɹ��ļ����е�������־д��һ���ļ�,logrec.txt��
		 * ��û����Գɹ�����־д����һ���ļ�Login.txt
		 * 
		 * ����һ�����ɵ�logs.txt�ļ�ɾ��
		 * 
		 */
		 if(!textLogFile.exists()){
		    	System.out.println("log.txt������");
		    	return false;
		    }
		//���ļ�ȡ����������10������
		//�������ڱ���������־�ļ���
		List<LogDate> ld=new ArrayList<LogDate>();
		/*
		 * �������ڱ�����Գɹ�����־����
		 */
		List<LogRec> lr=new ArrayList<LogRec>();
		//��log.txt�ļ������е�Ԫ����ӵ�list��
		ld.addAll(loadlogDate(textLogFile));
	         
	    //����ϴα�����ļ�δƥ��ɹ�����־�ļ��Ƿ����
	        
	    if(loginLogFile.exists()){
	    	ld.addAll(loadlogDate(loginLogFile));
	    }
	    //�ٴ���һ�����ϣ����ڱ����������û�ɹ�����־
	    List<LogDate> loginlist=new ArrayList<LogDate>();
	    //ѭ������Ե���־��������Թ���
	    for (LogDate login :ld){
	    	//׼�����
	    	if(login.getType()!=LogDate.TYPE_LOGIN){
	    		continue;
	    	}
	    	LogRec logRec=matchLogout(login, ld);
	    	if(logRec==null){
	    		//û����Գɹ�
	    		loginlist.add(login);
	    	}else{
	    		//��Գɹ�
	    		lr.add(logRec);
	    	}
	    }
	    //�ֱ𽫳ɹ���ʧ�ܵ���־д�벻ͬ���ļ�
	   try {
		   //д��ɹ���Ե���־
		   saveList(logRec,lr);
		   //д��û�гɹ���Ե���־
		   saveList(loginLogFile, loginlist);
		   //����һ�����������ı����ʮ����־�ļ�ɾ��
//		   textLogFile.delete();
			return true;
	} catch (Exception e) {
	}
		return false;
		
	}
	/**
	 * �������ļ����е�Ԫ��˳�����tostring���������غ���ַ�������д������ļ���
	 * @param file �ļ���
	 * @param list ������
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
	 * ��������
	 * ����Ե����ݷ������������˱���
	 * @return
	 */
	/**
	 * @return
	 */
	public boolean sendLogRecToServer(){
		 /**
		  * 1����Ҫ���ж�
		  *     1.1����鱣����Գɹ�����־�ļ��Ƿ����
		  * 2:��logrec.txt�ļ���ÿһ�����ݷ��͸���������
		  *    2.1������socket�������������
		  *    2.2��ͨ��Socket��ȡ���������������װΪ�����ַ��������
		  *    ���ڽ����ݷ��͸���������
		  *    2.3��������ȡlogrec.txt�ļ��Ļ����ַ�������
		  *    ���ڰ��ж�ȡÿһ�������־
		  *    2.4��˳���ȡ�ļ��е�ÿһ����־�������͸���������
		  * 3���Ͽ����ӣ��ͷ���Դ
		  */
		if(!logRec.exists()){
			System.out.println("�ļ�������");
			return false;
		}
		socket=null;//�������ӷ�����
		BufferedReader br=null;//���ڶ�ȡ�����־
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
			pw.flush();//��������������ȫ��д��
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
	//�ͻ��˿�ʼ�����ķ���
	public void start() throws IOException{
		/*
		 * ����ռ����ݵĹ���������
		 * 1����wtmpx�ļ���ȡ��һ�����ݣ�batch���������������������ַ����󱣴�
		 * 2�������������־���յ���ǳ����
		 * 3������Ե����ݷ��͵�������
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
