
package sp;

import java.awt.Graphics;
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
public class Background {

    private BufferedImage background;
    
    private final float BACKGROUND_SPEED = 0.35f;
    
    private float backgroundX = 0;
    private final int backgroundY = 0;
    
    protected Background() {
        
        try {
            
            final File backgroundFile = new File("src/sp/resources/background.jpg");
            background = ImageIO.read(backgroundFile);
            
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void update() {
        backgroundX -= BACKGROUND_SPEED;
        
        if (backgroundX * -1 >= background.getWidth()) {
            backgroundX = 0;
        }
    }
    
    protected void draw(final Graphics g) {
        g.drawImage(background, (int)backgroundX, backgroundY, null);
        g.drawImage(background, (int)backgroundX + background.getWidth(), backgroundY, null);
    }
}
