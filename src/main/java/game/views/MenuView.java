package game.views;

import game.GameFrame;
import game.gridgame.GridGameManager;
import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.awt.*;

public class MenuView extends JPanel {
    private static final Logger LOGGER = LogManager.getLogger();
    private final JButton startButton = new JButton();
    private final JButton quitButton = new JButton();

    public MenuView(GameFrame gameFrame, GridGameManager gridGameManager) {
        startButton.setText("Start Game");
        startButton.addActionListener((event) -> {
           LOGGER.debug("Game Starting");
           gameFrame.setView(gridGameManager.getGridGameCanvas());
           gridGameManager.beginGame();
        });

        JPanel panel = new JPanel(new MigLayout("","[fill, grow]","[fill,grow]"));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(panel, "cell 0 0");
        quitButton.setText("Quit Game");
        panel.add(startButton, "cell 0 0");
        panel.add(quitButton, "cell 0 1");
    }
}
