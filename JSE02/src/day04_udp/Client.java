package day04_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * ����UDPͨ�ŵĿͻ���
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
				//���ڴ���������������IP��InetAddress����
				 InetAddress address=InetAddress.getByName("127.0.0.1");
					int port=8088;
					//���ݱ�
					DatagramPacket sendPacket=new 	DatagramPacket(sendBuf, sendBuf.length, address,port);
					client.send(sendPacket);	
					 recvBuf=new byte[100];	
						DatagramPacket recvPacket=new DatagramPacket(recvBuf, recvBuf.length);
						client.receive(recvPacket);
						String recvStr=new String(recvPacket.getData(), 0, recvPacket.getLength());
						System.out.println("��������˵��"+recvStr);
			     }
	    } catch (Exception e) {
		}
	}
}
