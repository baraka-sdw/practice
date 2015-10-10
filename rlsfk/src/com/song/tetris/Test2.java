package com.song.tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Test2 extends JPanel{

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.BLUE);
		g.setFont(new Font(null, Font.BOLD, 50));
		g.drawString("ËÎ", 100, 100);
		g.drawString("µÂ", 100, 200);
		g.drawString("Î°", 100, 300);
		System.out.println(Thread.currentThread().getName());
	}
}
