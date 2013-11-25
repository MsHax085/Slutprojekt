
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
public class Character {

    private final EventListener eventListener;
    
    private final int x = 300;
    private float y = 290;
    private final int WIDTH = 68;
    private final int HEIGHT = 93;
    
    private float ySpeed = 0;
    private final float GRAVITY = 0.42f;
    private final float JUMP_IMPULSE = -9.6f;
    
    private BufferedImage[] sprites;
    private int spriteIndex = 1;
    private long lastSpriteChange = 0;
    
    protected Character(final EventListener eventListener) {
        this.eventListener = eventListener;
        
        try {
            // Load Character Sprites
            final File spritesFile = new File("src/sp/resources/character.png");
            final BufferedImage spritesImage = ImageIO.read(spritesFile);
            
            final int rows = spritesImage.getHeight() / HEIGHT;
            final int cols = spritesImage.getWidth() / WIDTH;
            
            this.sprites = new BufferedImage[rows * cols];
            
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    
                    sprites[(row * cols) + col] = spritesImage.getSubimage(
                            col * WIDTH,
                            row * HEIGHT,
                            WIDTH,
                            HEIGHT);
                    
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void update() {
        
        float yForce = 0;
        
        yForce += GRAVITY;
        if (y == 290 && eventListener.isSpacePressed()) {// row == 290 <=> NOT jumping
            yForce += JUMP_IMPULSE;
        }
        
        ySpeed += yForce;
        y += ySpeed;
        
        if (y > 290) {// Horizontal plane
            y = 290;
            ySpeed = 0;
        }
        
        if (System.currentTimeMillis() - lastSpriteChange > 50) {
            spriteIndex++;
            if (spriteIndex > 3) {
                spriteIndex = 1;
            }
            lastSpriteChange = System.currentTimeMillis();
        }
    }
    
    protected void draw(final Graphics g) {
        g.drawImage(sprites[spriteIndex], x, (int) (y - HEIGHT) + 1, null);
    }
    
    protected int getWidth() {
        return WIDTH;
    }
    
    protected int getHeight() {
        return HEIGHT;
    }
    
    protected int getX() {
        return x;
    }
    
    protected int getY() {
        return (int)y;
    }
}
