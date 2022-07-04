package game;

public class GridManager {

    public LocationType[][] grid = null;

    public GridManager(){
        grid = new LocationType[gameSize][gameSize];
        for (int i = 0; i < gameSize; i++) {
            for (int j = 0; j < gameSize; j++) {
                grid[i][j] = LocationType.EMPTY;
            }
        }

        grid[gameSize / 2][gameSize / 2] = LocationType.PLAYER;
    }

    public void updateGrid(int futX, int futY){
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                grid[i][j] = LocationType.EMPTY;
            }
        }
;        grid[futX][futY] = LocationType.PLAYER;
    }


    public int height = 600;
    public int width = 600;
    public final int gameSize = 40;
}
