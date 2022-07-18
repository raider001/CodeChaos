package game.gridgame;

import game.utils.enums.LocationType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

/**
 * Detects collisions between objects and returns which type the locations should be.
 */
public class GridCollisionManager {

    private static final Logger LOGGER = LogManager.getLogger(GridCollisionManager.class);

    /**
     * Detects a collision and returns what the grid cell type should be.
     *
     * @param oldType The existing type.
     * @param newType The type to replace.
     * @return Location Type.
     */
    public LocationType detectCollision(LocationType oldType, LocationType newType) {

        LocationType result = detectPlayerCollisions(oldType, newType);

        if (result == null) {
            result = detectEffectiveCollisions(oldType, newType);
        }

        if (result == null) {
            result = detectNonEffectiveCollisions(oldType, newType);
        }

        return result == null ? LocationType.EMPTY : result;
    }

    // Checking if the player hit enemies.
    private LocationType detectPlayerCollisions(LocationType oldType, LocationType newType) {
        Objects.requireNonNull(oldType);
        Objects.requireNonNull(newType);

        if (oldType == LocationType.PLAYER && (newType == LocationType.GHOST || newType == LocationType.RAT)) {
            LOGGER.debug("Oh no you got hit by " + newType);
            return newType;
        }

        if (newType == LocationType.PLAYER && (oldType == LocationType.GHOST || oldType == LocationType.RAT)) {
            LOGGER.debug("Oh no you got hit by " + oldType);
            return oldType;
        }
        return null;
    }

    // Checking if the effective spells hit enemies.
    private LocationType detectEffectiveCollisions(LocationType oldType, LocationType newType) {
        Objects.requireNonNull(oldType);
        Objects.requireNonNull(newType);

        if ((oldType == LocationType.ENERGY && newType == LocationType.GHOST) ||
            (newType == LocationType.ENERGY && oldType == LocationType.GHOST)) {
            LOGGER.debug("You destroyed " + newType + " with " + oldType);
            return LocationType.EMPTY;
        }

        if ((oldType == LocationType.FIRE && newType == LocationType.RAT) ||
            (newType == LocationType.FIRE && oldType == LocationType.RAT)) {
            LOGGER.debug("You destroyed " + newType + " with " + oldType);
            return LocationType.EMPTY;
        }
        return null;
    }

    // Checking non-effective spells hitting enemies.
    private LocationType detectNonEffectiveCollisions(LocationType oldType, LocationType newType) {
        Objects.requireNonNull(oldType);
        Objects.requireNonNull(newType);

        if ((oldType == LocationType.FIRE && newType == LocationType.GHOST) ||
            (newType == LocationType.FIRE && oldType == LocationType.GHOST)) {
            LOGGER.debug(newType + " does not effect " + oldType);
            return LocationType.GHOST;
        }

        if ((oldType == LocationType.ENERGY && newType == LocationType.RAT) ||
            (newType == LocationType.ENERGY && oldType == LocationType.RAT)) {
            LOGGER.debug(newType + " does not effect " + oldType);
            return LocationType.RAT;
        }
        return null;
    }
}
