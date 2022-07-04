package game;

import java.util.Objects;

public class MoveProcessor {

    Player player;
    GridManager gridManager;

    public MoveProcessor(GridManager gridManager){
        this.gridManager = gridManager;
        player = new Player(gridManager);
    }

    public void processMove(Direction direction){
        Objects.requireNonNull(direction);

        switch (direction) {
            case UP:
                if (player.getCurrentDirection() != Direction.DOWN) {
                    movePlayer(Direction.UP);
                }
                break;

            case DOWN:
                if (player.getCurrentDirection() != Direction.UP) {
                    movePlayer(Direction.DOWN);
                }
                break;

            case LEFT:
                if (player.getCurrentDirection() != Direction.RIGHT) {
                    movePlayer(Direction.LEFT);
                }
                break;

            case RIGHT:
                if (player.getCurrentDirection() != Direction.LEFT) {
                    movePlayer(Direction.RIGHT);
                }
                break;
        }
    }

    private void movePlayer(Direction direction) {
        player.setCurrentDirection(direction);

        int yMove = 0;
        int xMove = 0;
        switch (player.getCurrentDirection()) {
            case UP -> yMove = -1;
            case DOWN -> yMove = 1;
            case RIGHT -> xMove = 1;
            case LEFT -> xMove = -1;
            default -> throw new IllegalStateException(
                    "Unexpected value: " + player.getCurrentDirection());
        }

        int futX = player.getxLoc() + xMove;
        int futY = player.getyLoc() + yMove;

        if(futX < 0)
            futX = gridManager.gameSize - 1;
        if(futY < 0)
            futY = gridManager.gameSize - 1;
        if(futX >= gridManager.gameSize)
            futX = 0;
        if(futY >= gridManager.gameSize)
            futY = 0;

        player.setxLoc(futX);
        player.setYLoc(futY);

        gridManager.updateGrid(futX, futY);
    }
}
