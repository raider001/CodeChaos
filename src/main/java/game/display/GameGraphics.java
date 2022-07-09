package game.display;

import game.GridManager;
import game.key_processors.MoveProcessor;
import game.key_processors.SpellProcessor;
import game.utils.Dimensions;
import game.utils.enums.LocationType;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * Displays the game.
 */
public class GameGraphics {

    private final GridManager gridManager;
    private final KeyManager keyManager;
    private final WindowManager windowManager;

    private final Frame frame;
    private final Canvas canvas;
    private Graphics graph = null;
    private BufferStrategy strategy = null;

    public GameGraphics(GridManager gridManager, MoveProcessor moveProcessor,
                        SpellProcessor spellProcessor) {
        super();
        this.gridManager = gridManager;
        frame            = new Frame();
        canvas           = new Canvas();
        keyManager       = new KeyManager(moveProcessor, spellProcessor, frame, canvas);
        windowManager    = new WindowManager();
        initGraphics();
    }

    private void initGraphics() {
        frame.setSize(Dimensions.getWidth() + 7, Dimensions.getHeight() + 27);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        canvas.addKeyListener(keyManager);
        canvas.setSize(Dimensions.getWidth() + 7, Dimensions.getHeight() + 27);
        frame.add(canvas);
        frame.addWindowListener(windowManager);
        frame.dispose();
        frame.validate();
        frame.setTitle("Bilby Adventure!");
        frame.setVisible(true);

        canvas.setIgnoreRepaint(true);
        canvas.setBackground(Color.WHITE);

        canvas.createBufferStrategy(2);

        strategy = canvas.getBufferStrategy();
        graph    = strategy.getDrawGraphics();

        renderGame();
    }

    public void renderGame() {
        canvas.paint(graph);

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
            canvas.requestFocus();
        } while (strategy.contentsLost());
    }
}
