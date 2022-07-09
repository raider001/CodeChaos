package game.display;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowManager implements WindowListener {

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
}
