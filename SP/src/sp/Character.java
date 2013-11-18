
package sp;

import java.awt.Color;
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
public class Character {

    private final GamePanel gamePanel;
    
    private final int x = 100;
    private float y = 300;
    private final int WIDTH = 50;
    private final int HEIGHT = 51;
    
    private BufferedImage[] sprites;
    private int spriteIndex = 0;
    
    protected Character(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        
        try {
            // Load Character Sprites
            final File spritesFile = new File("src/sp/resources/character.png");
            final BufferedImage spritesImage = ImageIO.read(spritesFile);
            
            final int rows = spritesImage.getHeight() / HEIGHT;
            final int cols = spritesImage.getWidth() / WIDTH;
            
            this.sprites = new BufferedImage[rows * cols];
            
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    
                    sprites[(y * cols) + x] = spritesImage.getSubimage(
                            x * WIDTH,
                            y * HEIGHT,
                            WIDTH,
                            HEIGHT);
                    
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void update() {
        
    }
    
    protected void draw(final Graphics g) {
        
        g.drawImage(sprites[spriteIndex], x - (WIDTH / 2), (int) (y - HEIGHT), null);
        g.setColor(Color.red);
        g.fillOval(x, (int)y, 2,2);
    }
}
