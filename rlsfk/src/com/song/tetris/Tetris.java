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

/*俄罗斯方块面板*/
public class Tetris extends JPanel {
	private static final long serialVersionUID = 1L;
	public static final int CELL_SIZE = 26;
	public static final int ROWS = 20; // 行数
	public static final int COLS = 10;// 列数Column
	// 分数，墙，正在下落的方块，下一个方块
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
	private int index = 0;// 下落计数器
	private int level = 10;// 下落速度控制，越大越慢
	private int interval = 1000 / 20;// 主循环定时器间隔



	/* 软件开始的方法 */
	public void action() {
		/*
		 * wall[18][0] = new Cell(18, 0, Tetris.L); wall[19][0] = new Cell(19,
		 * 0, Tetris.L); wall[19][1] = new Cell(19, 1, Tetris.L); wall[19][2] =
		 * new Cell(19, 2, Tetris.L);
		 */

		tetromino = Tetromino.randomOne();// 生产下落方块
		nextOne = Tetromino.randomOne();// 生产下一个方块
//在action方法中添加键盘事件处理代码
//监听键盘事件：创建监听对象，注册监听器
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(key==KeyEvent.VK_Q){
				System.exit(0);
				}
				//拦截，只有在运行期间，处理后续案件事件
				switch (state) {
				case GAME_OVER://GAME_OVER=2
					if(key==KeyEvent.VK_S){
						wall=new Cell[ROWS][COLS];
						score=lines=0;
						index=0;
						state=RUNNING;
					}
					return;
					
				case PAUSE://PAUSE=1，暂停状态下，只能处理c按键
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
		// 在action方法中添加定时器循环控制
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

	 //在Tetris类中，重写绘制方法，绘制背景图片
	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		g.translate(15, 15);
		paintWall(g);//画墙
		paintTetromino(g);// 画正在下落的方块
		paintNextOne(g);//调用绘制下一个方块的方法
		paintScore(g);//在paint方法中增加分数绘制和游戏状态绘制
		paintState(g);
		System.out.println(Thread.currentThread().getName());
	}
    
	//分数绘制
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

	/* 遊戲狀態繪製 */
	private void paintState(Graphics g) {
		int x = 290;
		int y = 160 + 56 + 56;
		switch (state) {
		case RUNNING:
			g.drawString("[P]Pause", x, y);//改变状态
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

	/* 绘制paint下一個方塊 */
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

	// 在Tetris类中添加方法
	// 检查正在下落的方块是否出界
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

	// 右移动计算流程处理，要处理出界策略
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
	/* 检查正在下落的方块是否与墙上的方块重合 */
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

	/* 销毁已经满的行 */
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
//销毁已经满的行
	private void deleteLine(int row) {
		for (int i = row; i >= 1; i--) {
			System.arraycopy(wall[i - 1], 0, wall[i], 0, COLS);
		}
		/* fill填充，将第0行填充为null，清空第0行 */
		Arrays.fill(wall[0], null);
	}

	/* 判断下方是否有方块 */
	private boolean fullCells(int row) {
		Cell[] line = wall[row];

		for (int i = 0; i < line.length; i++) {/* line.length=20 */
			Cell cell = line[i];/* 默认col=0~9 */
			if (cell == null) {
				return false;

			}
		}
		return true;
	}

	/* 将不能下落的方块入墙 */
	public void landIntoWall() {
		Cell[] cells = tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int row = cell.getRow();
			int col = cell.getCol();
			wall[row][col] = cell;
		}
	}

	/* 检查正在下落的方块是否能够下落了 ,ROW，COL格子的坐标 */
	private boolean canDrop() {
		Cell[] cells = tetromino.cells;
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int row = cell.getRow();
			if (row == ROWS - 1) {
				return false;/* 到底不能下落 */
			}
		}
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int row = cell.getRow();
			int col = cell.getCol();
			if (wall[row + 1][col] != null) {
				return false;/* 下方有格子不能下落 */
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