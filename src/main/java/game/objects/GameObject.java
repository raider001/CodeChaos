package game.objects;

import game.utils.Location;

/**
 * All objects extend from this.
 */
public abstract class GameObject {

    private Location location;

    protected GameObject(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
