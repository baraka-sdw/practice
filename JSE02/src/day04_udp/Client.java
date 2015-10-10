package day04_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * 基于UDP通信的客户端
 * @author Administrator
 *
 */
public class Client {

	public static void main(String[] args) {
		DatagramSocket client=null;
		byte[] sendBuf=null;
		byte[] recvBuf=null;
		try {
			client=new DatagramSocket();
			while(true){
				Scanner sc=new Scanner(System.in);
				String str=sc.nextLine();
				 sendBuf=str.getBytes();
				//用于创建描述服务器端IP的InetAddress对象
				 InetAddress address=InetAddress.getByName("127.0.0.1");
					int port=8088;
					//数据报
					DatagramPacket sendPacket=new 	DatagramPacket(sendBuf, sendBuf.length, address,port);
					client.send(sendPacket);	
					 recvBuf=new byte[100];	
						DatagramPacket recvPacket=new DatagramPacket(recvBuf, recvBuf.length);
						client.receive(recvPacket);
						String recvStr=new String(recvPacket.getData(), 0, recvPacket.getLength());
						System.out.println("服务器端说："+recvStr);
			     }
	    } catch (Exception e) {
		}
	}
}
