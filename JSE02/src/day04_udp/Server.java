package day04_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	public static void main(String[] args) {
		//创建服务器端的DatagramSocket
		DatagramSocket server=null;
		String recvStr=null;
		int flag=1;
		try {
			while(flag==1){
				server=new DatagramSocket(8088);
				byte[] recvBuf=new byte[100];
				DatagramPacket recvPacket=new DatagramPacket(recvBuf, recvBuf.length);
				server.receive(recvPacket);
				recvStr=new String(recvPacket.getData(), 0, recvPacket.getLength());
				System.out.println("客户端说："+recvStr);
				recvPacket.getPort();
				recvPacket.getAddress();
				String sendStr="hello I'am Servrer";
				sendStr.getBytes();
			}
			server.close();
		} catch (Exception e) {
		}

	}

}
