package game.gridgame.display;

import game.gridgame.GridManager;
import game.GameFrame;
import game.gridgame.key_processors.MoveProcessor;
import game.gridgame.key_processors.SpellProcessor;
import game.utils.Dimensions;
import game.utils.enums.LocationType;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Displays the game.
 */
public class GameGraphics extends Canvas {

    private final GridManager gridManager;
    private final KeyManager keyManager;
    private final GameFrame frame;
    private Graphics graph = null;
    private BufferStrategy strategy = null;

    public GameGraphics(GameFrame gameFrame, GridManager gridManager, MoveProcessor moveProcessor,  SpellProcessor spellProcessor) {
        this.gridManager = gridManager;
        frame            = gameFrame;
        keyManager       = new KeyManager(moveProcessor, spellProcessor, frame, this);
    }

    public void startGame() {
        addKeyListener(keyManager);

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
                graph.setColor(LocationType.EMPTY.getColour());
                graph.fillRect(0, 0, Dimensions.getWidth(), Dimensions.getHeight());

                int gridUnit = Dimensions.getHeight() / Dimensions.GAME_SIZE;

                for (int i = 0; i < Dimensions.GAME_SIZE; i++) {
                    for (int j = 0; j < Dimensions.GAME_SIZE; j++) {
                        LocationType gridCase = gridManager.getGrid()[i][j];

                        graph.setColor(gridCase.getColour());
                        graph.fillRect(i * gridUnit, j * gridUnit, gridUnit, gridUnit);
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
