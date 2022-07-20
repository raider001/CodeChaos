package game.gridgame.key_processors;

import game.GameFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides the ability to change global key context and controls.
 */
public class KeyManager {

    private static final Logger LOGGER = LogManager.getLogger(KeyManager.class);

    private Component gameFrame;
    private Map<KeyAction, Runnable> keyActionMap = new HashMap<>();

    public KeyManager(Component gameFrame) {
        this.gameFrame = gameFrame;

        gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LOGGER.debug("key typed.");
                keyActionMap.getOrDefault(new KeyAction(KeyActionEvent.TYPED, e.getKeyCode()), () -> {}).run();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                LOGGER.debug("key pressed.");
                keyActionMap.getOrDefault(new KeyAction(KeyActionEvent.PRESSED, e.getKeyCode()), () -> {}).run();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                LOGGER.debug("key released.");
                keyActionMap.getOrDefault(new KeyAction(KeyActionEvent.RELEASED, e.getKeyCode()), () -> {}).run();
            }
        });
    }

    /**
     * Removes the old key context and replaces it with a new key context.
     * @param keyContext
     */
    public void setKeyContext(Map<KeyAction, Runnable> keyContext) {
        keyActionMap = keyContext;
    }
}
