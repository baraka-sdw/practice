package com.song.tetris;

import java.awt.image.BufferedImage;

public class Cell {
	private int row;
	private int col;
	private BufferedImage image;
	

	public Cell(int row, int col, BufferedImage image) {//�����ͼ
		super();
		this.row = row;
		this.col = col;
		this.image = image;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage T) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Cell [row=" + row + ", col=" + col + ", image=" + image + "]";
	}

	public void moveLeft() {
		col--;
	}

	public void moveRight() {
		col++;
	}

	public void drop() {
		row++;
	}
	public void moveUp() {
		row--;
	}
}
