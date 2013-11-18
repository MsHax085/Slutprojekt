
package sp;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Richard Dahlgren
 * @since 2013-okt-18
 * @version 1.0
 */
public class Character {

    private final GamePanel gamePanel;
    
    private final int x = 100;
    private float y = 300;
    private final int WIDTH = 67;
    private final int HEIGHT = 69;
    
    private BufferedImage sprites;
    private int spriteIndex = 0;
    
    protected Character(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        
        try {
            
            final File spritesFile = new File("src/sp/resources/character.png");
            sprites = ImageIO.read(spritesFile);
            
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void update() {
        
    }
    
    protected void draw() {
        
    }
}
