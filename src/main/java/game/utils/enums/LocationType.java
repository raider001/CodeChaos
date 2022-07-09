package game.utils.enums;

import java.awt.*;

/**
 * Types of cells in the game grid.
 */
public enum LocationType {

    EMPTY(new Color(252, 216, 169)),
    PLAYER(new Color(128, 207, 17)),
    GHOST(new Color(250, 254, 254)),
    RAT(new Color(139, 69, 45)),
    ENERGY(new Color(106, 13, 173)),
    FIRE(new Color(255, 0, 0));

    private final Color colour;

    LocationType(Color colour) {
        this.colour = colour;
    }

    public Color getColour() {
        return colour;
    }
}
