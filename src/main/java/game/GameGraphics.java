package game;

import game.key_processors.MoveProcessor;
import game.key_processors.SpellProcessor;
import game.utils.Dimensions;
import game.utils.enums.Direction;
import game.utils.enums.LocationType;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.util.Objects;

/**
 * Displays the game and processes keys.
 */
public class GameGraphics implements WindowListener, KeyListener {

    private final GridManager gridManager;
    private final MoveProcessor moveProcessor;
    private final SpellProcessor spellProcessor;

    private final Frame frame;
    private final Canvas canvas;
    private Graphics graph = null;
    private BufferStrategy strategy = null;

    public GameGraphics(GridManager gridManager, MoveProcessor moveProcessor,
                        SpellProcessor spellProcessor) {
        super();
        this.gridManager    = gridManager;
        this.moveProcessor  = moveProcessor;
        this.spellProcessor = spellProcessor;
        frame               = new Frame();
        canvas              = new Canvas();
        initGraphics();
    }

    private void initGraphics() {
        frame.setSize(Dimensions.getWidth() + 7, Dimensions.getHeight() + 27);
        frame.setResizable(false);
        frame.setLocationByPlatform(true);
        canvas.addKeyListener(this);
        canvas.setSize(Dimensions.getWidth() + 7, Dimensions.getHeight() + 27);
        frame.add(canvas);
        frame.addWindowListener(this);
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

    @Override
    public void keyPressed(KeyEvent ke) {
        Objects.requireNonNull(ke);

        switch (ke.getKeyCode()) {
            case KeyEvent.VK_UP -> moveProcessor.processMove(Direction.UP);
            case KeyEvent.VK_DOWN -> moveProcessor.processMove(Direction.DOWN);
            case KeyEvent.VK_LEFT -> moveProcessor.processMove(Direction.LEFT);
            case KeyEvent.VK_RIGHT -> moveProcessor.processMove(Direction.RIGHT);
            case KeyEvent.VK_SPACE -> spellProcessor.castSpell();
            case KeyEvent.VK_SHIFT -> spellProcessor.switchSpell();
            case KeyEvent.VK_F11 -> resizeWindow();
            default -> {
                // Unsupported key
            }
        }
    }

    private void resizeWindow() {
        Dimension dim;
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        if ((Dimensions.getHeight() != dim.height - 50) ||
            (Dimensions.getWidth() != dim.height - 50)) {
            Dimensions.setHeight(dim.height - 50);
            Dimensions.setWidth(dim.height - 50);
        } else {
            Dimensions.setHeight(600);
            Dimensions.setWidth(600);
        }
        frame.setSize(Dimensions.getWidth() + 7,
                      Dimensions.getHeight() + 27);
        canvas.setSize(Dimensions.getWidth() + 7,
                       Dimensions.getHeight() + 27);
        canvas.validate();
        frame.validate();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // Not required.
    }

    @Override
    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        // Not required.
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // Not required.
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // Not required.
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // Not required.
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // Not required.
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not required.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not required.
    }
}
