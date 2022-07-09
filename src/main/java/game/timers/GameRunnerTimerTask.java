package game.timers;

import game.display.GameGraphics;
import game.key_processors.MoveProcessor;
import game.objects.Player;

import java.util.TimerTask;

/**
 * Updates the game on a timer.
 */
public class GameRunnerTimerTask extends TimerTask {

    private final GameGraphics gameGraphics;
    private final MoveProcessor moveProcessor;
    private final Player player;

    public GameRunnerTimerTask(GameGraphics gameGraphics, MoveProcessor moveProcessor, Player player) {
        this.gameGraphics  = gameGraphics;
        this.moveProcessor = moveProcessor;
        this.player        = player;
    }

    @Override
    public void run() {
        gameGraphics.renderGame();
        moveProcessor.processMove(player.getDirection());
    }
}
