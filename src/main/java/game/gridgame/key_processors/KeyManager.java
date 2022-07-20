package game.gridgame.key_processors;

import game.GameFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

/**
 * Provides the ability to change global key context and controls.
 */
public class KeyManager {
    public GameFrame gameFrame;

    private Map<KeyAction, Runnable> keyActionMap = new HashMap<>();

    public KeyManager(GameFrame gameFrame) {
        this.gameFrame = gameFrame;

        gameFrame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                keyActionMap.getOrDefault(new KeyAction(KeyActionEvent.TYPED, e.getKeyChar()), () -> {}).run();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                keyActionMap.getOrDefault(new KeyAction(KeyActionEvent.PRESSED, e.getKeyChar()), () -> {}).run();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keyActionMap.getOrDefault(new KeyAction(KeyActionEvent.RELEASED, e.getKeyChar()), () -> {}).run();
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
