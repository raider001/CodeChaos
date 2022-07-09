package game;

import game.utils.Dimensions;
import game.utils.Location;
import game.utils.enums.LocationType;

import java.util.Objects;

/**
 * Manages the grid of the game.
 */
public class GridManager {

    private final CollisionManager collisionManager;
    private LocationType[][] grid = null;

    public GridManager() {
        setGrid(new LocationType[Dimensions.GAME_SIZE][Dimensions.GAME_SIZE]);
        for (int i = 0; i < Dimensions.GAME_SIZE; i++) {
            for (int j = 0; j < Dimensions.GAME_SIZE; j++) {
                getGrid()[i][j] = LocationType.EMPTY;
            }
        }

        getGrid()[Dimensions.GAME_SIZE / 2][Dimensions.GAME_SIZE / 2] =
                LocationType.PLAYER;

        collisionManager = new CollisionManager();
    }

    public LocationType[][] getGrid() {
        return grid;
    }

    public void setGrid(LocationType[][] grid) {
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
    public void updateGridWithLocType(Location previousLoc, Location newLoc, LocationType locType) {
        Objects.requireNonNull(previousLoc);
        Objects.requireNonNull(newLoc);
        Objects.requireNonNull(locType);

        setLocationType(previousLoc, LocationType.EMPTY);

        if (getLocationType(newLoc) != LocationType.EMPTY) {
            LocationType collisionDetectionLocType = collisionManager.detectCollision(getLocationType(newLoc), locType);
            setLocationType(newLoc, collisionDetectionLocType);
            setLocationType(previousLoc, collisionDetectionLocType);
        }

        setLocationType(newLoc, locType);
    }

    /**
     * Adds an enemy type to the grid.
     *
     * @param loc     The location to add the enemy.
     * @param locType The enemy's location type.
     */
    public void addEnemyToGrid(Location loc, LocationType locType) {
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
        setLocationType(location, LocationType.EMPTY);
    }

    private LocationType getLocationType(Location location) {
        Objects.requireNonNull(location);
        return getGrid()[location.getXLoc()][location.getYLoc()];
    }

    private void setLocationType(Location location, LocationType locationType) {
        Objects.requireNonNull(location);
        getGrid()[location.getXLoc()][location.getYLoc()] = locationType;
    }
}
