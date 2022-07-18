package game.gridgame;

import com.kalynx.lwdi.DependencyInjectionException;
import com.kalynx.lwdi.DependencyInjector;
import game.GameLauncher;
import game.gridgame.display.GameGraphics;
import game.GameFrame;
import game.gridgame.key_processors.MoveProcessor;
import game.gridgame.key_processors.SpellProcessor;
import game.objects.Player;
import game.gridgame.timers.EnemyGeneratorTimerTask;
import game.gridgame.timers.GameRunnerTimerTask;

import java.util.Timer;

/**
 * Runs the game.
 */
public class GridGameManager {

    static final DependencyInjector GRID_GAME_MANAGER_INJECTOR = new DependencyInjector();

    public static final int SPELL_DELAY = 0;
    private static final int GAME_DELAY = 0;
    private static final int ENEMY_DELAY = 2000;

    private static final int GAME_PERIOD = 80;
    public static final int SPELL_PERIOD = GAME_PERIOD - 20;
    private static final int ENEMY_PERIOD = 5000;

    private final Timer enemyTimer = new Timer();
    private final Timer gameTimer = new Timer();

    private final GameRunnerTimerTask gameRunnerTimerTask;
    private final EnemyGeneratorTimerTask enemyGeneratorTimerTask;
    private final GameGraphics gameGraphics;


    public GridGameManager() throws DependencyInjectionException {
        GRID_GAME_MANAGER_INJECTOR.inject(Player.class);
        GRID_GAME_MANAGER_INJECTOR.inject(GridManager.class);
        GRID_GAME_MANAGER_INJECTOR.inject(MoveProcessor.class);
        GRID_GAME_MANAGER_INJECTOR.inject(SpellProcessor.class);
        /*
         TODO - This is a little strange pulling the dependency from one injector to another...
         It may make more sense having the frame containing the canvases to determine which one should be used. rather than
         the game screen controlling the overall window. That would resolve this issue.
         */
        GRID_GAME_MANAGER_INJECTOR.add(GameLauncher.GAME_LAUNCHER_DI.getDependency(GameFrame.class));
        gameGraphics = GRID_GAME_MANAGER_INJECTOR.inject(GameGraphics.class);
        gameRunnerTimerTask = GRID_GAME_MANAGER_INJECTOR.inject(GameRunnerTimerTask.class);
        enemyGeneratorTimerTask = GRID_GAME_MANAGER_INJECTOR.inject(EnemyGeneratorTimerTask.class);
    }

    public GameGraphics getGridGameCanvas() {
        return GRID_GAME_MANAGER_INJECTOR.getDependency(GameGraphics.class);
    }

    /**
     * Kicks off the timers for the game.
     */
    public void beginGame() {
        gameGraphics.startGame();
        gameTimer.schedule(gameRunnerTimerTask, GAME_DELAY, GAME_PERIOD);
        enemyTimer.schedule(enemyGeneratorTimerTask, ENEMY_DELAY, ENEMY_PERIOD);
    }

}
