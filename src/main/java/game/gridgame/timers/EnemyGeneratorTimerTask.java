package game.gridgame.timers;

import game.gridgame.GridManager;
import game.objects.Enemy;
import game.utils.Dimensions;
import game.utils.Location;
import game.utils.enums.EnemyType;
import game.utils.enums.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

/**
 * Generates a new enemy on a timer.
 */
public class EnemyGeneratorTimerTask extends TimerTask {

    private final List<Enemy> enemies = new ArrayList<>();
    private final GridManager gridManager;
    private final Random rand = new Random();

    public EnemyGeneratorTimerTask(GridManager gridManager) {
        this.gridManager = gridManager;
    }

    @Override
    public void run() {
        Location location = new Location(rand.nextInt(Dimensions.GAME_SIZE),
                                         rand.nextInt(Dimensions.GAME_SIZE));
        Enemy enemy = new Enemy(location, rand.nextInt(EnemyType.values().length));
        enemies.add(enemy);

        gridManager.addEnemyToGrid(location, getLocationType(enemy.getEnemyType()));

    }

    private EntityType getLocationType(EnemyType enemyType) {
        return enemyType == EnemyType.GHOST ? EntityType.GHOST : EntityType.RAT;
    }
}
