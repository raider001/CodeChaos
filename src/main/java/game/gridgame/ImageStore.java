package game.gridgame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ImageStore {
    private static final Logger LOGGER = LogManager.getLogger(ImageStore.class);
    private final Map<String, Image> images = new HashMap<>();

    public void addImage(String imageId, Path imageLocation) {
        try {
            Image img = ImageIO.read(getClass().getClassLoader().getResource(imageLocation.toString()));
            images.put(imageId, img);
        } catch(IOException e) {
            LOGGER.error("Unable to read image from " + imageLocation.toString());
        }
    }

    public Image getImage(String imageId) {
        return images.get(imageId);
    }
}
