package day03;

import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.SliderUI;

/**
 * ��дһ�������ô����ڰ�ɫ���ɫ֮���л�
 * 
 * @author Administrator
 * 
 *         1:��TestDemo1�̳���JFrame 2����TestDemo1ʵ��Runnable�ӿ� 3����дrun���������ڷ������л��ڰ���ɫ
 *         4����main������ʵ������ǰ�࣬���ô��ڴ�С�ɼ� 5�����߳�������
 * 
 */
public class TestDemo1 extends JFrame implements Runnable {
	static Thread t1;
	JPanel jp1;
	JFrame jf;

	public TestDemo1() {
		jf = new JFrame();
		jp1 = new JPanel();
		jf.setSize(200, 300);
		jp1.setSize(200, 300);
		jf.add(jp1);
		jf.setVisible(true);

	}

	public static void main(String[] args) throws InterruptedException {
		TestDemo1 r1 = new TestDemo1();
		t1 = new Thread(r1);
		t1.start();
	}

	@Override
	public void run() {
		int i = 0;
		while (true) {
			i = i == 0 ? 1 : 0;
			if (i == 0) {
				jp1.setBackground(Color.black);
			} else {
				jp1.setBackground(Color.white);
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
