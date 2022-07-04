package src.main.java;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;

public class GameGraphics implements KeyListener, WindowListener {
	
	private Frame frame = null;
	private Canvas canvas = null;
	private Graphics graph = null;
	private BufferStrategy strategy = null;
	private int[][] grid = null;
	private int[][] playerLocation = null;
	private int height = 600;
	private int width = 600;
	private int gameSize = 40;
	private boolean running = true;
	
	public final static int EMPTY = 0;
	public final static int PLAYER = 1;
	
	public final static int UP = 0;
	public final static int DOWN = 1;
	public final static int LEFT = 2;
	public final static int RIGHT = 3;
	

	private int direction = -1;
	private int next_direction = -1;
	
	public GameGraphics() {
		super();
		frame = new Frame();
		canvas = new Canvas();
		
		grid = new int[gameSize][gameSize];
		playerLocation = new int[gameSize * gameSize][2];
	}
	
	public void initGraphics() {

		frame.setSize(width + 7, height + 27);
		frame.setResizable(false);
		frame.setLocationByPlatform(true);
		canvas.setSize(width + 7, height + 27);
		frame.add(canvas);
		canvas.addKeyListener(this);
		frame.addWindowListener(this);
		frame.dispose();
		frame.validate();
		frame.setTitle("Bilby Adventure!");
		frame.setVisible(true);

		canvas.setIgnoreRepaint(true);
		canvas.setBackground(Color.WHITE);
		
		canvas.createBufferStrategy(2);
		
		strategy = canvas.getBufferStrategy();
		graph = strategy.getDrawGraphics();

		initGame();

		renderGame();
	}
	
	private void initGame() {
		
		for (int i = 0; i < gameSize; i++) {
			for (int j = 0; j < gameSize; j++) {
				grid[i][j] = EMPTY;
			}
		}

		for (int i = 0; i < gameSize * gameSize; i++) {
			playerLocation[i][0] = -1;
			playerLocation[i][1] = -1;
		}

		playerLocation[0][0] = gameSize/2;
		playerLocation[0][1] = gameSize/2;
		grid[gameSize/2][gameSize/2] = PLAYER;

	}
	
	public void renderGame() {
		int gridUnit = height / gameSize;
		canvas.paint(graph);

		do {
			do {
				graph = strategy.getDrawGraphics();
				((Graphics2D)graph).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Draw Background
				graph.setColor(new Color(252, 216, 169));
				graph.fillRect(0, 0, width, height);

				int gridCase = EMPTY;

				for (int i = 0; i < gameSize; i++) {
					for (int j = 0; j < gameSize; j++) {
						gridCase = grid[i][j];

						switch (gridCase) {
						case PLAYER:
							graph.setColor(new Color(128, 207, 17));
							graph.fillRect(i * gridUnit, j * gridUnit,
									gridUnit, gridUnit);
							break;
							
						default:
							break;
						}
					}
				}

				graph.setFont(new Font(Font.SANS_SERIF, Font.BOLD, height / 40));
				graph.setColor(Color.BLACK);

				graph.dispose();
			} while (strategy.contentsRestored());

			// Draw image from buffer
			strategy.show();
			Toolkit.getDefaultToolkit().sync();
			canvas.requestFocus();
		} while (strategy.contentsLost());
	}
	
	public void movePlayer() {
		direction = next_direction;
		if (direction < 0) {
			return;
		}
		int ymove = 0;
		int xmove = 0;
		switch (direction) {
		case UP:
			xmove = 0;
			ymove = -1;
			break;
		case DOWN:
			xmove = 0;
			ymove = 1;
			break;
		case RIGHT:
			xmove = 1;
			ymove = 0;
			break;
		case LEFT:
			xmove = -1;
			ymove = 0;
			break;
		default:
			xmove = 0;
			ymove = 0;
			break;
		}

		int tempx = playerLocation[0][0];
		int tempy = playerLocation[0][1];

		int fut_x = playerLocation[0][0] + xmove;
		int fut_y = playerLocation[0][1] + ymove;
		
		if(fut_x < 0)
			fut_x = gameSize - 1;
		if(fut_y < 0)
			fut_y = gameSize - 1;
		if(fut_x >= gameSize)
			fut_x = 0;
		if(fut_y >= gameSize)
			fut_y = 0;

		playerLocation[0][0] = fut_x;
		playerLocation[0][1] = fut_y;
		
		grid[tempx][tempy] = EMPTY;

		int x, y, i;

		for (i = 1; i < gameSize * gameSize; i++) {
			if ((playerLocation[i][0] < 0) || (playerLocation[i][1] < 0)) {
				break;
			}
			grid[playerLocation[i][0]][playerLocation[i][1]] = EMPTY;
			x = playerLocation[i][0];
			y = playerLocation[i][1];
			playerLocation[i][0] = tempx;
			playerLocation[i][1] = tempy;
			tempx = x;
			tempy = y;
		}

		grid[playerLocation[0][0]][playerLocation[0][1]] = PLAYER;
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		int code = ke.getKeyCode();
		Dimension dim;

		switch (code) {
		case KeyEvent.VK_UP:
			if (direction != DOWN) {
				next_direction = UP;
			}

			break;

		case KeyEvent.VK_DOWN:
			if (direction != UP) {
				next_direction = DOWN;
			}

			break;

		case KeyEvent.VK_LEFT:
			if (direction != RIGHT) {
				next_direction = LEFT;
			}

			break;

		case KeyEvent.VK_RIGHT:
			if (direction != LEFT) {
				next_direction = RIGHT;
			}

			break;

		case KeyEvent.VK_F11:
			dim = Toolkit.getDefaultToolkit().getScreenSize();
			if ((height != dim.height - 50) || (width != dim.height - 50)) {
				height = dim.height - 50;
				width = dim.height - 50;
			} else {
				height = 600;
				width = 600;
			}
			frame.setSize(width + 7, height + 27);
			canvas.setSize(width + 7, height + 27);
			canvas.validate();
			frame.validate();
			break;

		default:
			// Unsupported key
			break;
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		running = false;
		System.exit(0);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
