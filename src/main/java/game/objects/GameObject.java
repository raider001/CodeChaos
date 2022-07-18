package game.objects;

import game.utils.Location;

/**
 * All objects extend from this.
 */
public abstract class GameObject {

    private Location location;

    private String imageId;

    GameObject(String imageId) {

    }

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
