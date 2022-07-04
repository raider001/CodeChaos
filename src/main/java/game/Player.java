package game;

import java.util.Objects;

public class Player {

    private Direction currentDirection;
    private int xLoc;
    private int yLoc;

    public Player(GridManager gridManager){
        Objects.requireNonNull(gridManager);
        xLoc = gridManager.gameSize / 2;
        yLoc = gridManager.gameSize / 2;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public int getxLoc() {
        return xLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public void setYLoc(int yLoc) {
        this.yLoc = yLoc;
    }
}
