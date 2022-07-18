package game.gridgame.display;

import game.gridgame.key_processors.MoveProcessor;
import game.gridgame.key_processors.SpellProcessor;
import game.utils.Dimensions;
import game.utils.enums.Direction;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

/**
 * Manages key presses.
 */
public record KeyManager(MoveProcessor moveProcessor,
                         SpellProcessor spellProcessor, Frame frame,
                         Canvas canvas) implements KeyListener {

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
    public void keyTyped(KeyEvent e) {
        // Not required.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not required.
    }
}
