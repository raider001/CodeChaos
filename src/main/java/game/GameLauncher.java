package game;

import com.kalynx.lwdi.DependencyInjectionException;
import com.kalynx.lwdi.DependencyInjector;
import game.gridgame.GridGameManager;
import game.views.MenuView;

/**
 * Main entry point.
 */
public class GameLauncher {

    public static final DependencyInjector GAME_LAUNCHER_DI = new DependencyInjector();

    public static void main(String[] args) throws DependencyInjectionException {
        GameFrame gameFrame = GAME_LAUNCHER_DI.inject(GameFrame.class);

        GAME_LAUNCHER_DI.inject(GridGameManager.class);
        MenuView menuView = GAME_LAUNCHER_DI.inject(MenuView.class);
        gameFrame.setView(menuView);
        /*
        Green = player. Move with WASD. Wraps around screen.
        Change spell = Shift. Cast spell = space. Spells do not wrap around screen.
        Purple = energy defeats white = ghost.
        Red = fire defeats brown = rat.
         */
    }
}
