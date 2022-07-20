package game.views;

import game.charactercreator.CharacterCreatorCanvas;
import game.gridgame.ImageStore;
import game.gridgame.key_processors.KeyAction;
import game.gridgame.key_processors.KeyActionEvent;
import game.gridgame.key_processors.KeyManager;
import net.miginfocom.swing.MigLayout;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CharacterCreatorView extends JPanel {

    private List<String> heads = new ArrayList<>();
    int headIndex = 0;
    private final ImageStore imageStore = new ImageStore();
    private static final Logger LOGGER = LogManager.getLogger();
    private final CharacterCreatorCanvas canvas = new CharacterCreatorCanvas();

    public CharacterCreatorView() throws IOException {
        super(new MigLayout("","0[grow, shrink]0","0[grow, shrink]0"));
        add(canvas, "");
        String headLocation = "images/characterparts/head/";
        URL url = getClass().getClassLoader().getResource("images/characterparts/head/");
        File[] files = new File(url.getPath()).listFiles();

        for(File file: files) {
            heads.add(file.getName());
            imageStore.addImage(file.getName(), Path.of(headLocation, file.getName()));
        }
    }

   public void initialise() {
       KeyManager keyManager = new KeyManager(canvas);
       Map<KeyAction, Runnable> actionMap = new HashMap<>();
       actionMap.put(new KeyAction(KeyActionEvent.TYPED, 'a'), () -> {
           System.out.println("stuff");
           headIndex--;
           if(headIndex < 0) {
               headIndex = heads.size() - 1;
           }
           canvas.setHead(imageStore.getImage(heads.get(headIndex)), heads.get(headIndex));
           getRootPane().repaint();
       });
       keyManager.setKeyContext(actionMap);
   }

   @Override
    protected void paintComponent(Graphics g) {
        canvas.render(g);
    }
}
