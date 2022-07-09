package game.utils;

/**
 * Holds the dimensions of the game.
 */
public class Dimensions {

    public static final int GAME_SIZE = 40;
    private static int height = 600;
    private static int width = 600;

    private Dimensions() {
        throw new IllegalStateException("Utility Class.");
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        Dimensions.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public static void setWidth(int width) {
        Dimensions.width = width;
    }
}
