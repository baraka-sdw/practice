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
		g.drawString("��", 100, 100);
		g.drawString("��", 100, 200);
		g.drawString("ΰ", 100, 300);
		System.out.println(Thread.currentThread().getName());
	}
}
