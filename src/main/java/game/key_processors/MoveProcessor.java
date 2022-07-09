package game.key_processors;

import game.GridManager;
import game.objects.Player;
import game.utils.Dimensions;
import game.utils.Location;
import game.utils.enums.Direction;
import game.utils.enums.LocationType;

import java.util.Objects;

/**
 * Processes a move for a player.
 */
public record MoveProcessor(GridManager gridManager, Player player) {

    /**
     * Processes a move by the player.
     *
     * @param direction The direction to change to.
     */
    public void processMove(Direction direction) {
        Objects.requireNonNull(direction);

        switch (direction) {
            case UP:
                if (player.getDirection() != Direction.DOWN) {
                    movePlayer(Direction.UP);
                }
                break;

            case DOWN:
                if (player.getDirection() != Direction.UP) {
                    movePlayer(Direction.DOWN);
                }
                break;

            case LEFT:
                if (player.getDirection() != Direction.RIGHT) {
                    movePlayer(Direction.LEFT);
                }
                break;

            case RIGHT:
                if (player.getDirection() != Direction.LEFT) {
                    movePlayer(Direction.RIGHT);
                }
                break;
        }
    }

    private void movePlayer(Direction direction) {
        player.getDirection(direction);

        int yMove = 0;
        int xMove = 0;
        switch (player.getDirection()) {
            case UP -> yMove--;
            case DOWN -> yMove++;
            case RIGHT -> xMove++;
            case LEFT -> xMove--;
            default -> throw new IllegalStateException(
                    "Unexpected value: " + player.getDirection());
        }

        int futX = player.getLocation().getXLoc() + xMove;
        int futY = player.getLocation().getYLoc() + yMove;

        if (futX < 0) {
            futX = Dimensions.GAME_SIZE - 1;
        }
        if (futY < 0) {
            futY = Dimensions.GAME_SIZE - 1;
        }
        if (futX >= Dimensions.GAME_SIZE) {
            futX = 0;
        }
        if (futY >= Dimensions.GAME_SIZE) {
            futY = 0;
        }

        Location newPlayerLoc = new Location(futX, futY);
        gridManager.updateGridWithLocType(player.getLocation(),
                                          newPlayerLoc, LocationType.PLAYER);
        player.setLocation(newPlayerLoc);
    }
}
