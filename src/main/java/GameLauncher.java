package src.main.java;

public class GameLauncher {

	public static void main(String[] args) {
		GameGraphics gameGraphics = new GameGraphics();
		gameGraphics.initGraphics();
		GameManager gameManager = new GameManager(gameGraphics);
		gameManager.beginGame();
	}

}
