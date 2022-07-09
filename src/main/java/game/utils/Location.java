package game.utils;

import java.util.Objects;

/**
 * Contains location details.
 */
public class Location {
    private final int xLoc;
    private final int yLoc;

    public Location() {
        xLoc = Dimensions.GAME_SIZE / 2;
        yLoc = Dimensions.GAME_SIZE / 2;
    }

    public Location(int x, int y) {
        xLoc = x;
        yLoc = y;
    }

    public int getXLoc() {
        return xLoc;
    }

    public int getYLoc() {
        return yLoc;
    }

    @Override
    public String toString() {
        return "Location{" +
               "xLoc=" + xLoc +
               ", yLoc=" + yLoc +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location = (Location) o;
        return xLoc == location.xLoc && yLoc == location.yLoc;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xLoc, yLoc);
    }
}
