package game;

import game.utils.Dimensions;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

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
        JPanel panel = new JPanel(new MigLayout("","0[grow]0","0[grow]0"));
        setContentPane(panel);

        panel.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
                repaint();
            }

            @Override
            public void componentMoved(ComponentEvent e) {
                revalidate();
                repaint();

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });
    }

    public void setView(Component component, String constraints) {
        getContentPane().removeAll();
        getContentPane().add(component, constraints);
        revalidate();
        repaint();
    }
}
