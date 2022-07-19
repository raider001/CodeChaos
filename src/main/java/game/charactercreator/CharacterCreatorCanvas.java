package game.charactercreator;

import java.awt.*;

public class CharacterCreatorCanvas extends Canvas {

    public void render() {
        paint(getBufferStrategy().getDrawGraphics());
    }

    @Override
    public void paint(Graphics graphics) {
        Rectangle bounds = graphics.getClipBounds();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0,0,bounds.width, bounds.height);
    }
}
