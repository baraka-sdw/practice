package day03;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * ʹ�������ڲ���ķ�ʽʵ�ִ�����ɫ�л�
 * @author Administrator
 *
 */
public class TestDemo2 {
	public static void main(String[] args) {
		JFrame frame=new JFrame();
		final JPanel panel=new JPanel();
		frame.setSize(200,300);
		panel.setSize(200,300);
		frame.setVisible(true);
		frame.add(panel);
		Thread t1=new Thread(){
			public void run(){
				int i=0;
				while(true){
					i=i==0?1:0;
					if(i==0){
						panel.setBackground(Color.black);
					}else{
						panel.setBackground(Color.white);
					}
				}
			}
		};
		t1.start();
		
	}
	

}
