package day01;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import com.song.tetris.Tetris;

public class CubeFrame extends JFrame {
	private int cubeX = 0, cubeY = 0;
	int max;
	Timer timer;

	public CubeFrame() {
		setSize(350, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		CubePanel panel = new CubePanel();
		add(panel);
		KeyListener l = new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			};

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				// cubeX+=30;
				// repaint();
			}

			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				switch (key) {
				case KeyEvent.VK_RIGHT:
					cubeX += 30;
					break;
				case KeyEvent.VK_LEFT:
					cubeX -= 30;
					break;
				case KeyEvent.VK_UP:
					cubeY -= 30;
					break;
				case KeyEvent.VK_DOWN:
					cubeY += 30;
					break;
				}
				repaint();

			}
		};

		panel.addKeyListener(l);
		panel.setFocusable(true);
		panel.requestFocus();
		timer = new Timer();
		TimerTask dropTask = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				cubeY += 10;

				CubeFrame.this.repaint();
			}
		};
timer.schedule(dropTask, 1000,1000);
	}

	public static void main(String[] args) {
		CubeFrame cubeframe = new CubeFrame();
		cubeframe.setVisible(true);
		
		

	}

	private class CubePanel extends JPanel {
		public void paint(Graphics g) {
			// g.setColor(new Color(0xfff000));
			// g.fillRect(0, 0, getWidth(), getHeight());
			// g.setColor(new Color(0xffff0));
			g.fillRoundRect(cubeX + 10, cubeY + 10, 30, 30, 5, 5);
			g.drawRoundRect(0, 0, 30, 30, 5, 5);
			g.fill3DRect(35, 35, 20, 20, true);
			g.draw3DRect(35, 35, 20, 20, true);
			Polygon p = new Polygon();
			p.addPoint(80, 40);
			p.addPoint(80, 60);
			p.addPoint(100, 50);
			p.addPoint(100, 20);
			p.addPoint(300, 200);
			g.drawPolygon(p);
			g.fillOval(90, 50, 30, 40);

		}
	}

}
