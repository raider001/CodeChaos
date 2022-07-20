package game.charactercreator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

public class CharacterCreatorCanvas extends Canvas {

    private final Logger LOGGER = LogManager.getLogger(CharacterCreatorCanvas.class);

    private Image head;
    private String headName;

    private final String title = "Character Creator";

    private Rectangle referenceSize = null;
    private Rectangle bounds = new Rectangle();

    public void render() {
        if(getBufferStrategy() == null) {
            createBufferStrategy(2);
        }
        render(getBufferStrategy().getDrawGraphics());
        getBufferStrategy().show();
    }

    public void setHead(Image head, String headName) {
        this.head = head;
        this.headName = headName;
        render();
    }

    public void render(Graphics graphics) {

        if(referenceSize == null) {
            referenceSize = graphics.getClipBounds();
        }

        if(graphics.getClipBounds() != null ) {
            bounds = graphics.getClipBounds();
        }

        graphics.setColor(Color.WHITE);
        graphics.fillRect(0,0,bounds.width, bounds.height);
        graphics.setColor(Color.BLACK);

        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        graphics.drawString(title,  (bounds.width / 2) - (graphics.getFontMetrics().stringWidth(title) / 2),25);
        // Calculate the scale % for images
        int width = (int)((double) bounds.width / referenceSize.width * 300);
        int height = (int)((double) bounds.height / referenceSize.height * 300);

        // center logic
        int centerX = bounds.width / 2 - width / 2;
        int centerY = bounds.height / 2 - height / 2;
        if(head != null) {
            graphics.drawImage(head,centerX,centerY,width,height, null);
        }
        requestFocus();
    }

    @Override
    public void paint(Graphics g) {
        render(g);
    }
}
