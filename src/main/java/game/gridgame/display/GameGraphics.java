package game.gridgame.display;

import game.gridgame.GridManager;
import game.GameFrame;
import game.gridgame.ImageStore;
import game.gridgame.key_processors.*;
import game.utils.Dimensions;
import game.utils.enums.Direction;
import game.utils.enums.EntityType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.util.HashMap;
import java.util.Map;

/**
 * Displays the game.
 */
public class GameGraphics extends Canvas {

    private final GridManager gridManager;
    private final GameFrame frame;
    private Graphics graph = null;
    private BufferStrategy strategy = null;
    private final MoveProcessor moveProcessor;
    private final SpellProcessor spellProcessor;
    private final ImageStore imageStore;
    public GameGraphics(GameFrame gameFrame, GridManager gridManager, MoveProcessor moveProcessor,  SpellProcessor spellProcessor, ImageStore imageStore) {
        this.gridManager = gridManager;
        frame            = gameFrame;
        this.imageStore = imageStore;
        this.moveProcessor = moveProcessor;
        this.spellProcessor = spellProcessor;
    }

    public void startGame() {

        KeyManager keyManager = new KeyManager(this);
        // TODO - Step in the right direction, but this likely won't work when switching between views. Likely to double actions up
        Map<KeyAction, Runnable> actionMap = new HashMap<>();
        actionMap.put(new KeyAction(KeyActionEvent.RELEASED, KeyEvent.VK_UP), () ->  moveProcessor.processMove(Direction.UP));
        actionMap.put(new KeyAction(KeyActionEvent.RELEASED, KeyEvent.VK_DOWN), () -> moveProcessor.processMove(Direction.DOWN));
        actionMap.put(new KeyAction(KeyActionEvent.RELEASED, KeyEvent.VK_LEFT), () -> moveProcessor.processMove(Direction.LEFT));
        actionMap.put(new KeyAction(KeyActionEvent.RELEASED, KeyEvent.VK_RIGHT), () -> moveProcessor.processMove(Direction.RIGHT));
        actionMap.put(new KeyAction(KeyActionEvent.RELEASED, KeyEvent.VK_SPACE), () -> spellProcessor.castSpell());
        actionMap.put(new KeyAction(KeyActionEvent.RELEASED, KeyEvent.VK_SHIFT), () -> spellProcessor.switchSpell());

        keyManager.setKeyContext(actionMap);

        setSize(frame.getSize());

        setIgnoreRepaint(true);
        setBackground(Color.WHITE);

        createBufferStrategy(2);

        strategy = getBufferStrategy();
        graph = strategy.getDrawGraphics();

        renderGame();
    }

    public void renderGame() {
        paint(graph);

        do {
            do {
                graph = strategy.getDrawGraphics();
                ((Graphics2D) graph).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                                      RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw Background
                graph.setColor(Color.GREEN);
                graph.fillRect(0, 0, Dimensions.getWidth(), Dimensions.getHeight());

                int gridUnit = Dimensions.getHeight() / Dimensions.GAME_SIZE;

                for (int i = 0; i < Dimensions.GAME_SIZE; i++) {
                    for (int j = 0; j < Dimensions.GAME_SIZE; j++) {
                        EntityType gridCase = gridManager.getGrid()[i][j];
                        Image img = imageStore.getImage(gridCase.getImage());
                        graph.drawImage(img, i * gridUnit, j * gridUnit,Dimensions.GAME_SIZE, Dimensions.GAME_SIZE, null);
                    }
                }

                graph.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Dimensions.getHeight() / 40));
                graph.setColor(Color.BLACK);
                graph.dispose();
            } while (strategy.contentsRestored());

            // Draw image from buffer
            strategy.show();
            Toolkit.getDefaultToolkit().sync();
            requestFocus();
        } while (strategy.contentsLost());
    }
}
