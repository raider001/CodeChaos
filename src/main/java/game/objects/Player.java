package game.objects;

import game.utils.Location;
import game.utils.enums.Direction;

/**
 * Contains details of the player.
 */
public class Player extends MovingObject {

    public Player() {
        super(new Location(), Direction.RIGHT);
    }
}
