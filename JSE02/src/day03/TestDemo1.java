package day03;

import java.awt.Color;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.SliderUI;

/**
 * 编写一个程序，让窗口在白色与黑色之间切换
 * 
 * @author Administrator
 * 
 *         1:让TestDemo1继承自JFrame 2：让TestDemo1实现Runnable接口 3：重写run方法，并在方法中切换黑白两色
 *         4：在main方法中实例化当前类，设置窗口大小可见 5：在线程中启动
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
