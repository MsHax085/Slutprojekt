
package sp;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
public class PauseMenu {
    
    private BufferedImage green_button;
    private BufferedImage red_button;
    private final Font font = new Font("Arial", Font.BOLD, 48);

    private final int RESUME_BUTTON_X = 400;
    private final int RESUME_BUTTON_Y = 180;
    
    private final int EXIT_BUTTON_X = 0;
    private final int EXIT_BUTTON_Y = 0;
    
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 50;
    
    protected PauseMenu() {
        
        try {
            final File green_button_file = new File("src/sp/resources/green_button.png");
            final File red_button_file = new File("src/sp/resources/red_button.png");
            
            green_button = ImageIO.read(green_button_file);
            red_button = ImageIO.read(red_button_file);
        
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected boolean isStartResumeClicked() {
        return false;
    }
    
    protected boolean isExitClicked() {
        return false;
    }
    
    protected void draw(final Graphics graphics) {
        
        final Graphics2D g = (Graphics2D) graphics;
        // Smoother text
        RenderingHints rh = new RenderingHints(
                            RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g.setRenderingHints(rh);
        
        g.setColor(Color.BLACK);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));
        g.fillRect(0, 50, 1000, 100);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));// Reset
        
        final FontMetrics metrics = g.getFontMetrics(font);
        final String gameOver = "GAME OVER";
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString("GAME OVER", (1000 / 2) - (metrics.stringWidth(gameOver) / 2), 50 + (100 - (metrics.getHeight() / 2)));
        
        //g.fillRect(RESUME_BUTTON_X, RESUME_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }
}
