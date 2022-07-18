package game.gridgame;

import game.utils.enums.EntityType;
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
    public EntityType detectCollision(EntityType oldType, EntityType newType) {

        EntityType result = detectPlayerCollisions(oldType, newType);

        if (result == null) {
            result = detectEffectiveCollisions(oldType, newType);
        }

        if (result == null) {
            result = detectNonEffectiveCollisions(oldType, newType);
        }

        return result == null ? EntityType.EMPTY : result;
    }

    // Checking if the player hit enemies.
    private EntityType detectPlayerCollisions(EntityType oldType, EntityType newType) {
        Objects.requireNonNull(oldType);
        Objects.requireNonNull(newType);

        if (oldType == EntityType.PLAYER && (newType == EntityType.GHOST || newType == EntityType.RAT)) {
            LOGGER.debug("Oh no you got hit by " + newType);
            return newType;
        }

        if (newType == EntityType.PLAYER && (oldType == EntityType.GHOST || oldType == EntityType.RAT)) {
            LOGGER.debug("Oh no you got hit by " + oldType);
            return oldType;
        }
        return null;
    }

    // Checking if the effective spells hit enemies.
    private EntityType detectEffectiveCollisions(EntityType oldType, EntityType newType) {
        Objects.requireNonNull(oldType);
        Objects.requireNonNull(newType);

        if ((oldType == EntityType.ENERGY && newType == EntityType.GHOST) ||
            (newType == EntityType.ENERGY && oldType == EntityType.GHOST)) {
            LOGGER.debug("You destroyed " + newType + " with " + oldType);
            return EntityType.EMPTY;
        }

        if ((oldType == EntityType.FIRE && newType == EntityType.RAT) ||
            (newType == EntityType.FIRE && oldType == EntityType.RAT)) {
            LOGGER.debug("You destroyed " + newType + " with " + oldType);
            return EntityType.EMPTY;
        }
        return null;
    }

    // Checking non-effective spells hitting enemies.
    private EntityType detectNonEffectiveCollisions(EntityType oldType, EntityType newType) {
        Objects.requireNonNull(oldType);
        Objects.requireNonNull(newType);

        if ((oldType == EntityType.FIRE && newType == EntityType.GHOST) ||
            (newType == EntityType.FIRE && oldType == EntityType.GHOST)) {
            LOGGER.debug(newType + " does not effect " + oldType);
            return EntityType.GHOST;
        }

        if ((oldType == EntityType.ENERGY && newType == EntityType.RAT) ||
            (newType == EntityType.ENERGY && oldType == EntityType.RAT)) {
            LOGGER.debug(newType + " does not effect " + oldType);
            return EntityType.RAT;
        }
        return null;
    }
}
