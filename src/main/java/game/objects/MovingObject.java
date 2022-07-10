package game.objects;

import game.utils.Location;
import game.utils.enums.Direction;

/**
 * All objects that can move extend from this.
 */
public abstract class MovingObject extends GameObject {

    private Direction currentDirection;

    protected MovingObject(Location location, Direction direction) {
        super(location);
        this.currentDirection = direction;
    }

    public Direction getDirection() {
        return currentDirection;
    }

    public void getDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }
}
