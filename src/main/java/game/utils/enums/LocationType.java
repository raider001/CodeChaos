package game.utils.enums;

import java.awt.*;

/**
 * Types of cells in the game grid.
 */
public enum LocationType {

    EMPTY(null),
    PLAYER("mage"),
    GHOST("ghost"),
    RAT("rat"),
    ENERGY("electricity"),
    FIRE("fireball");

    private final String image;

    LocationType(String imageLoc) {
        this.image = imageLoc;
    }

    public String getImage() {
        return image;
    }
}
