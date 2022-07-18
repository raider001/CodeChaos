package game;

import game.GameLauncher;
import game.utils.Dimensions;
import game.views.MenuView;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Provides the basic frame for the game.
 */
public class GameFrame extends JFrame {
    public GameFrame() {
        setSize(Dimensions.getWidth() + 7, Dimensions.getHeight() + 27);
        setResizable(false);
        setLocationByPlatform(true);
        dispose();
        validate();
        setTitle("Bilby Adventure!");
        setVisible(true);
        setResizable(true);
        setIgnoreRepaint(true);
        setBackground(Color.WHITE);
        createBufferStrategy(2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new MigLayout("","0[]0","0[]0"));
        JPanel panel = new JPanel(new MigLayout("","0[]0","0[]0"));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setContentPane(panel);
    }

    public void setView(Component component) {
        getContentPane().removeAll();
        getContentPane().add(component, "");
        revalidate();
    }
}
