package game.views;

import game.GameFrame;
import game.gridgame.GridGameManager;
import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class MenuView extends JPanel {
    private static final Logger LOGGER = LogManager.getLogger();
    private final JButton startButton = new JButton();
    private final JButton quitButton = new JButton();
    private final JButton characterCreatorButton = new JButton();

    public MenuView(GameFrame gameFrame, GridGameManager gridGameManager, CharacterCreatorView characterCreatorView) {
        super(new MigLayout("","[grow, fill]","[grow]"));
        startButton.setText("Start Game");
        startButton.addActionListener(event -> {
           LOGGER.debug("Game Starting");
           gameFrame.setView(gridGameManager.getGridGameCanvas(), "top");
           gridGameManager.beginGame();
        });

        characterCreatorButton.setText("Character Creator");
        characterCreatorButton.addActionListener(event -> {
           gameFrame.setView(characterCreatorView, "growx, growy");
        });

        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        quitButton.setText("Quit Game");
        add(startButton, "cell 0 0");
        add(characterCreatorButton, "cell 0 1");
        add(quitButton, "cell 0 2");
    }
}
