package game.utils.enums;

/**
 * Types of cells in the game grid.
 */
public enum EntityType {

    EMPTY(null),
    PLAYER("mage"),
    GHOST("ghost"),
    RAT("rat"),
    ENERGY("electricity"),
    FIRE("fireball");

    private final String image;

    EntityType(String imageLoc) {
        this.image = imageLoc;
    }

    public String getImage() {
        return image;
    }
}
