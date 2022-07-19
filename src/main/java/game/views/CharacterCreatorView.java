package game.views;

import game.GameFrame;
import game.charactercreator.CharacterCreatorCanvas;
import game.gridgame.GridGameManager;
import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class CharacterCreatorView extends JPanel {

    private static final Logger LOGGER = LogManager.getLogger();
    private final CharacterCreatorCanvas canvas = new CharacterCreatorCanvas();
    public CharacterCreatorView() {
        super(new MigLayout("","0[grow,fill]0","0[grow]0"));
        add(canvas, "growx, growy");
        addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                LOGGER.debug(e.getComponent().getWidth());
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }
}
