
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

    private final int gamePanelWidth;
    private BufferedImage background;
    
    private final float BACKGROUND_SPEED = 0.35f;
    
    private float backgroundX = 0;
    private final int backgroundY = 0;
    
    protected Background(final int width) {
        
        this.gamePanelWidth = width;
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
        
        final int backgroundWidth = background.getWidth();
        
        for (int x = 0; x < Math.ceil((double) gamePanelWidth / (double) backgroundWidth) + 1; x++) {// + 1 - scrolling background
            g.drawImage(background, (int)backgroundX + (x * backgroundWidth), backgroundY, null);
        }
    }
}
