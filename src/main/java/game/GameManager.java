package game;

import game.key_processors.MoveProcessor;
import game.key_processors.SpellProcessor;
import game.objects.Enemy;
import game.objects.Player;
import game.timers.EnemyGeneratorTimerTask;
import game.timers.GameRunnerTimerTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Runs the game.
 */
public class GameManager {

    public static final int SPELL_DELAY = 0;
    private static final int GAME_DELAY = 0;
    private static final int ENEMY_DELAY = 2000;

    private static final int GAME_PERIOD = 80;
    public static final int SPELL_PERIOD = GAME_PERIOD - 20;
    private static final int ENEMY_PERIOD = 5000;

    private final GameGraphics gameGraphics;
    private final MoveProcessor moveProcessor;
    private final Player player;
    private final GridManager gridManager;

    private final List<Enemy> enemies = new ArrayList<>();

    private final Timer enemyTimer = new Timer();
    private final Timer gameTimer = new Timer();


    public GameManager() {
        player        = new Player();
        gridManager   = new GridManager();
        moveProcessor = new MoveProcessor(gridManager, player);

        SpellProcessor spellProcessor = new SpellProcessor(player, gridManager);

        gameGraphics = new GameGraphics(gridManager, moveProcessor, spellProcessor);
    }

    /**
     * Kicks off the timers for the game.
     */
    public void beginGame() {
        gameTimer.schedule(new GameRunnerTimerTask(gameGraphics, moveProcessor, player), GAME_DELAY, GAME_PERIOD);
        enemyTimer.schedule(new EnemyGeneratorTimerTask(enemies, gridManager), ENEMY_DELAY, ENEMY_PERIOD);
    }
}
