package game;

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
import java.util.Objects;

public class GameGraphics implements KeyListener, WindowListener {

	GridManager gridManager;
	MoveProcessor movementProcessor;

	private Frame frame = null;
	private Canvas canvas = null;
	private Graphics graph = null;
	private BufferStrategy strategy = null;

	public GameGraphics(GridManager gridManager) {
		super();
		this.gridManager = gridManager;
		this.movementProcessor = new MoveProcessor(gridManager);
		frame = new Frame();
		canvas = new Canvas();
		initGraphics();
	}

	public void initGraphics() {
		frame.setSize(gridManager.width + 7, gridManager.height + 27);
		frame.setResizable(false);
		frame.setLocationByPlatform(true);
		canvas.setSize(gridManager.width + 7, gridManager.height + 27);
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

		renderGame();
	}
	
	public void renderGame() {
		canvas.paint(graph);

		do {
			do {
				graph = strategy.getDrawGraphics();
				((Graphics2D)graph).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

				// Draw Background
				graph.setColor(new Color(252, 216, 169));
				graph.fillRect(0, 0, gridManager.width, gridManager.height);

				int gridUnit = gridManager.height / gridManager.gameSize;

				for (int i = 0; i < gridManager.gameSize; i++) {
					for (int j = 0; j < gridManager.gameSize; j++) {
						LocationType gridCase = gridManager.grid[i][j];

						if (gridCase == LocationType.PLAYER){
							graph.setColor(new Color(128, 207, 17));
							graph.fillRect(i * gridUnit, j * gridUnit,
										   gridUnit, gridUnit);
						}
					}
				}

				graph.setFont(new Font(Font.SANS_SERIF, Font.BOLD,
									   gridManager.height / 40));
				graph.setColor(Color.BLACK);

				graph.dispose();
			} while (strategy.contentsRestored());

			// Draw image from buffer
			strategy.show();
			Toolkit.getDefaultToolkit().sync();
			canvas.requestFocus();
		} while (strategy.contentsLost());
	}
	
	@Override
	public void keyPressed(KeyEvent ke) {
		Objects.requireNonNull(ke);
		int code = ke.getKeyCode();
		Dimension dim;

		switch (code) {
			case KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT -> movementProcessor.processMove(
					Direction.UP);
			case KeyEvent.VK_F11 -> {
				dim = Toolkit.getDefaultToolkit().getScreenSize();
				if ((gridManager.height != dim.height - 50) ||
					(gridManager.width != dim.height - 50)) {
					gridManager.height = dim.height - 50;
					gridManager.width  = dim.height - 50;
				} else {
					gridManager.height = 600;
					gridManager.width  = 600;
				}
				frame.setSize(gridManager.width + 7, gridManager.height + 27);
				canvas.setSize(gridManager.width + 7, gridManager.height + 27);
				canvas.validate();
				frame.validate();
			}
			default -> {
				// Unsupported key
			}

		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// Not required.
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// Not required.
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// Not required.
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// Not required.
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// Not required.
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// Not required.
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Not required.
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Not required.
	}
}
