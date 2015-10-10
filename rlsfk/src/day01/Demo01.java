package day01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Demo01 {
	public static void main(String[] args) {

		MainFrame frame = new MainFrame();
		frame.setVisible(true);
	}

}

class MainFrame extends JFrame {
	private int a;

	public MainFrame() {
		StagePanel pane = new StagePanel();
		this.setSize(400, 300);
		this.add(pane);
	}

	class StagePanel extends JPanel {
		public void paint(Graphics g) {
			g.setColor(Color.red);
			g.fillRect(10, 20, getWidth(), getHeight());
			// g.draw3DRect(30, 40, getWidth(), getHeight(), true);
			g.drawRect(10, 20, getWidth(), getHeight());
		}

	}

}
