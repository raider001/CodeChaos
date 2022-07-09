package game.objects;

import game.utils.Location;
import game.utils.enums.EnemyType;

/**
 * Contains enemy details.
 */
public class Enemy extends GameObject {

    private final EnemyType enemyType;

    public Enemy(Location location, int enemyTypeVal) {
        super(location);
        enemyType = EnemyType.values()[enemyTypeVal];
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }
}
