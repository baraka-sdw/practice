package com.song.tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.tarena.tetris.Tetromino;

/*����˹�������*/
public class Tetris extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int CELL_SIZE = 26;
	public static final int ROWS = 20; // ����
	public static final int COLS = 10;// ����Column
	// ������ǽ����������ķ��飬��һ������
	private int score;
	private int lines;
	private Cell[][] wall = new Cell[ROWS][COLS];
	private Tetromino tetromino;
	private Tetromino nextOne;
	private static BufferedImage background;
	private static BufferedImage overImage;
	public static BufferedImage T;
	public static BufferedImage S;
	public static BufferedImage Z;
	public static BufferedImage J;
	public static BufferedImage L;
	public static BufferedImage I;
	public static BufferedImage O;
	static {
		try {
			background = ImageIO.read(Tetris.class.getResource("tetris.png"));
			overImage = ImageIO.read(Tetris.class.getResource("game-over.png"));
			T = ImageIO.read(Tetris.class.getResource("T.png"));
			S = ImageIO.read(Tetris.class.getResource("S.png"));
			Z = ImageIO.read(Tetris.class.getResource("Z.png"));
			J = ImageIO.read(Tetris.class.getResource("J.png"));
			L = ImageIO.read(Tetris.class.getResource("L.png"));
			I = ImageIO.read(Tetris.class.getResource("I.png"));
			O = ImageIO.read(Tetris.class.getResource("O.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private int state;
	public static final int RUNNING = 0;
	public static final int PAUSE = 1;
	public static final int GAME_OVER = 2 ;
	
	
	private Timer loopTimer;
	private int index = 0;// ���������
	private int level = 10;// �����ٶȿ��ƣ�Խ��Խ��
	private int interval = 1000 / 20;// ��ѭ����ʱ�����



	/* �����ʼ�ķ��� */
	public void action() {
		/*
		 * wall[18][0] = new Cell(18, 0, Tetris.L); wall[19][0] = new Cell(19,
		 * 0, Tetris.L); wall[19][1] = new Cell(19, 1, Tetris.L); wall[19][2] =
		 * new Cell(19, 2, Tetris.L);
		 */

		tetromino = Tetromino.randomOne();// �������䷽��
		nextOne = Tetromino.randomOne();// ������һ������
//��action��������Ӽ����¼��������
//���������¼���������������ע�������
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key==KeyEvent.VK_Q){
				System.exit(0);
				}
				//���أ�ֻ���������ڼ䣬������������¼�
				switch (state) {
				case GAME_OVER://GAME_OVER=2
					if(key==KeyEvent.VK_S){
						wall=new Cell[ROWS][COLS];
						score=lines=0;
						index=0;
						state=RUNNING;
					}
					return;
					
				case PAUSE://PAUSE=1����ͣ״̬�£�ֻ�ܴ���c����
					if(key==KeyEvent.VK_C){
						state=RUNNING;
					}
					return;
				}
				switch (key) {
				case KeyEvent.VK_RIGHT:
					moveRightAction();
					break;
				case KeyEvent.VK_LEFT:
					moveLeftAction();
					break;
				case KeyEvent.VK_DOWN:
					softDropAction();
					break;
				case KeyEvent.VK_UP:
					rotateLeftAction();
					break;
				case KeyEvent.VK_P:
					state=PAUSE;
				case KeyEvent.VK_SPACE:
					hardDropActin();
					break;
				default:
					break;

				}
				repaint();
			}

		});
		this.setFocusable(true);
		this.requestFocus();
		// ��action��������Ӷ�ʱ��ѭ������
		loopTimer = new Timer();
		loopTimer.schedule(new TimerTask() {

			public void run() {
				if (state == RUNNING) {//RUNNING
					index++;
					if (index % level == 0) {
						softDropAction();
					}
				}
				repaint();
			}

		}, 0, interval);

	}

	 //��Tetris���У���д���Ʒ��������Ʊ���ͼƬ
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		g.translate(15, 15);
		paintWall(g);//��ǽ
		paintTetromino(g);// ����������ķ���
		paintNextOne(g);//���û�����һ������ķ���
		paintScore(g);//��paint���������ӷ������ƺ���Ϸ״̬����
		paintState(g);
		System.out.println(Thread.currentThread().getName());
	}
    
	//��������
	private void paintScore(Graphics g) {
		int x = 290;
		int y = 160;
		Font f = new Font(Font.SERIF, Font.HANGING_BASELINE, 40);
		g.setFont(f);
		g.setColor(new Color(0x161565));
		g.drawString("LINES:" + lines, x, y);
		y += 56;
		g.drawString("SCORE:" + score, x, y);
	}

	@Override
	public void paintAll(Graphics g) {
	}

	/* �[���B�L�u */
	private void paintState(Graphics g) {
		int x = 290;
		int y = 160 + 56 + 56;
		switch (state) {
		case RUNNING:
			g.drawString("[P]Pause", x, y);//�ı�״̬
			break;
		case PAUSE:
			g.drawString("[C]Continue", x, y);
			break;
		case GAME_OVER:
			g.drawString("[S]Restart", x, y);
			g.drawImage(overImage, -15, -15, null);
		}
	}

	private void paintTetromino(Graphics g) {
		if (tetromino == null) {
			return;
		}

		Cell[] cells = tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int x = cell.getCol() * CELL_SIZE;
			int y = cell.getRow() * CELL_SIZE;
			g.drawImage(cell.getImage(), x, y, null);

		}

	}

	/* ����paint��һ�����K */
	private void paintNextOne(Graphics g) {

		if (nextOne == null) {
			return;
		}
		Cell[] cells = nextOne.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int x = (cell.getCol() + 10) * CELL_SIZE;
			int y = (cell.getRow() + 1) * CELL_SIZE;
			g.drawImage(cell.getImage(), x, y, null);

		}
	}

	private void paintWall(Graphics g) {
		for (int row = 0; row < (wall.length); row++) {
			Cell[] line = wall[row];
			for (int col = 0; col < line.length; col++) {
				Cell cell = line[col];
				int x = col * CELL_SIZE;
				int y = row * CELL_SIZE;
				if (cell == null) {
					g.setColor(Color.blue);
					g.drawRect(x, y, CELL_SIZE, CELL_SIZE);

				} else {
					g.drawImage(cell.getImage(), x, y, null);
				}
			}

		}

	}

	// ��Tetris������ӷ���
	// �����������ķ����Ƿ����
	public boolean outOfBounds() {
		Cell[] cells = tetromino.cells;	
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int row = cell.getRow();
			int col = cell.getCol();
			if (col < 0 || col >= COLS || row < 0 || row >= ROWS) {
				return true;
			}
		}
		return false;
	}

	// ���ƶ��������̴���Ҫ����������
	public void moveRightAction() {
		tetromino.moveRight();
		if (outOfBounds() || conside()) {
			tetromino.moveLeft();

		}
	}

	public void moveLeftAction() {
		tetromino.moveLeft();
		if (outOfBounds() || conside()) {
			tetromino.moveRight();

		}
	}

	//
	/* �����������ķ����Ƿ���ǽ�ϵķ����غ� */
	private boolean conside() {
		Cell[] cells = tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int row = cell.getRow();
			int col = cell.getCol();
			if (row >= 0 && row < ROWS && col >= 0 && col <= COLS
					&& wall[row][col] != null) {
				return true;

			}
		}
		return false;
	}


	 public int [] scoreTable = { 0, 10, 30, 60, 100 };

	public void softDropAction() {
		if (canDrop()) {
			tetromino.softDrop();
		} else {
			landIntoWall();
			int lines = destoryLines();// 0 1 2 3 4
			this.lines += lines;
			this.score += scoreTable[lines];
			if (!isGameOver()) {
				tetromino = nextOne;
				nextOne = Tetromino.randomOne();

			} else {
				state = GAME_OVER;
			}

		}
	}

	public void hardDropActin() {
		while (canDrop()) {
			tetromino.softDrop();

		}
		landIntoWall();
		int lines = destoryLines();
		this.lines += lines;
		this.score += scoreTable[lines];
		if (!isGameOver()) {
			tetromino = nextOne;
			nextOne = Tetromino.randomOne();
		} else {
			state = GAME_OVER;
		}

	}

	private boolean isGameOver() {
		Cell[] cells = nextOne.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int row = cell.getRow();
			int col = cell.getCol();
			if (wall[row][col] != null) {
				return true;
			}
		}
		return false;
	}

	/* �����Ѿ������� */
	private int destoryLines() {
		int lines = 0;
		for (int row = 0; row < ROWS; row++) {
			if (fullCells(row)) {
				deleteLine(row);
				lines++;
			}
		}
		return lines;
	}
//�����Ѿ�������
	private void deleteLine(int row) {
		for (int i = row; i >= 1; i--) {
			System.arraycopy(wall[i - 1], 0, wall[i], 0, COLS);
		}
		/* fill��䣬����0�����Ϊnull����յ�0�� */
		Arrays.fill(wall[0], null);
	}

	/* �ж��·��Ƿ��з��� */
	private boolean fullCells(int row) {
		Cell[] line = wall[row];

		for (int i = 0; i < line.length; i++) {/* line.length=20 */
			Cell cell = line[i];/* Ĭ��col=0~9 */
			if (cell == null) {
				return false;

			}
		}
		return true;
	}

	/* ����������ķ�����ǽ */
	public void landIntoWall() {
		Cell[] cells = tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int row = cell.getRow();
			int col = cell.getCol();
			wall[row][col] = cell;
		}
	}

	/* �����������ķ����Ƿ��ܹ������� ,ROW��COL���ӵ����� */
	private boolean canDrop() {
		Cell[] cells = tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int row = cell.getRow();
			if (row == ROWS - 1) {
				return false;/* ���ײ������� */
			}
		}
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int row = cell.getRow();
			int col = cell.getCol();
			if (wall[row + 1][col] != null) {
				return false;/* �·��и��Ӳ������� */
			}
		}
		return true;
	}

	public void rotateRightAction() {
		tetromino.rotateRight();
		if (outOfBounds() || conside()) {  
			tetromino.rotateLeft();
		}
	}

	public void rotateLeftAction() {
		tetromino.rotateLeft();
		if (outOfBounds() || conside()) {
			tetromino.rotateRight();

		}
	}

}