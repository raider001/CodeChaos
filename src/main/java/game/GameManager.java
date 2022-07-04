package game;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager {
	
	private GameGraphics gameGraphics;

	private final boolean running = true;
	private final long speed = 70;
	
	public GameManager(){
		GridManager gridManager = new GridManager();
		this.gameGraphics = new GameGraphics(gridManager);
	}
	
	public void beginGame() {
		while (running) {
			gameGraphics.renderGame();
			gameGraphics.movementProcessor.processMove(Direction.RIGHT);
			long cycleTime = System.currentTimeMillis();
			cycleTime = System.currentTimeMillis() - cycleTime;
			long sleepTime = speed - cycleTime;
			if (sleepTime < 0)
				sleepTime = 0;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException ex) {
				Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null,
						ex);
				Thread.currentThread().interrupt();
			}
		}
	}
}
