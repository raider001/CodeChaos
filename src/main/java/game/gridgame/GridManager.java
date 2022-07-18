package game.gridgame;

import game.utils.Dimensions;
import game.utils.Location;
import game.utils.enums.EntityType;

import java.util.Objects;

/**
 * Manages the grid of the game.
 */
public class GridManager {

    private final GridCollisionManager collisionManager;
    private EntityType[][] grid = null;

    public GridManager() {
        setGrid(new EntityType[Dimensions.GAME_SIZE][Dimensions.GAME_SIZE]);
        for (int i = 0; i < Dimensions.GAME_SIZE; i++) {
            for (int j = 0; j < Dimensions.GAME_SIZE; j++) {
                getGrid()[i][j] = EntityType.EMPTY;
            }
        }

        getGrid()[Dimensions.GAME_SIZE / 2][Dimensions.GAME_SIZE / 2] =
                EntityType.PLAYER;

        collisionManager = new GridCollisionManager();
    }

    public EntityType[][] getGrid() {
        return grid;
    }

    public void setGrid(EntityType[][] grid) {
        this.grid = grid;
    }


    /**
     * Updates the grid with the new player's location by blanking out every
     * square except for the location passed.
     *
     * @param previousLoc Previous location.
     * @param newLoc      New Location.
     * @param locType     The new location type.
     */
    public void updateGridWithLocType(Location previousLoc, Location newLoc, EntityType locType) {
        Objects.requireNonNull(previousLoc);
        Objects.requireNonNull(newLoc);
        Objects.requireNonNull(locType);

        setLocationType(previousLoc, EntityType.EMPTY);


        if (getLocationType(newLoc) != EntityType.EMPTY) {
            EntityType collisionDetectionLocType = collisionManager.detectCollision(getLocationType(newLoc), locType);
            setLocationType(newLoc, collisionDetectionLocType);
            setLocationType(previousLoc, collisionDetectionLocType);
            return;
        }

        setLocationType(newLoc, locType);
    }

    /**
     * Adds an enemy type to the grid.
     *
     * @param loc     The location to add the enemy.
     * @param locType The enemy's location type.
     */
    public void addEnemyToGrid(Location loc, EntityType locType) {
        Objects.requireNonNull(loc);
        Objects.requireNonNull(locType);
        setLocationType(loc, locType);
    }

    /**
     * Clears a cell.
     *
     * @param location The cell's location.
     */
    public void clearCell(Location location) {
        Objects.requireNonNull(location);
        setLocationType(location, EntityType.EMPTY);
    }

    private EntityType getLocationType(Location location) {
        Objects.requireNonNull(location);
        return getGrid()[location.getXLoc()][location.getYLoc()];
    }

    private void setLocationType(Location location, EntityType locationType) {
        Objects.requireNonNull(location);
        getGrid()[location.getXLoc()][location.getYLoc()] = locationType;
    }
}
