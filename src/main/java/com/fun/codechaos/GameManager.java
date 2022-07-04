package com.fun.codechaos;

import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager {
	
	private GameGraphics gameGraphics;
	
	private long cycleTime = 0;
	private long sleepTime = 0;
	private boolean running = true;

	private long speed = 70;
	
	public GameManager(GameGraphics gameGraphics){
		this.gameGraphics = gameGraphics;
	}
	
	public void beginGame() {

		while (running) {
			cycleTime = System.currentTimeMillis();
			gameGraphics.movePlayer();
			gameGraphics.renderGame();
			cycleTime = System.currentTimeMillis() - cycleTime;
			sleepTime = speed - cycleTime;
			if (sleepTime < 0)
				sleepTime = 0;
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException ex) {
				Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null,
						ex);
			}
		}
	}
}
