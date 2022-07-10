package game;

/**
 * Main entry point.
 */
public class GameLauncher {

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.beginGame();

        /*
        Green = player. Move with WASD. Wraps around screen.
        Change spell = tab. Cast spell = space. Spells do not wrap around screen.
        Purple = energy defeats white = ghost.
        Red = fire defeats brown = rat.
         */
    }
}
