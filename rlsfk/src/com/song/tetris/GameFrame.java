package com.song.tetris;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	private Tetris tetris;
	private Test2 test2;
	public GameFrame() {
		System.out.println("...??");
		tetris = new Tetris();
		test2=new Test2();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(test2);
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_Z){
					JFrame j=new JFrame();
					setSize(541, 580);	
					add(tetris);
				}
			}
			
		});
		setSize(540, 580);
		setLocationRelativeTo(null);
	}

	public static void main(String[] a) {
		GameFrame frame = new GameFrame();
		frame.tetris.action();
		frame.setVisible(true);
	}
}

