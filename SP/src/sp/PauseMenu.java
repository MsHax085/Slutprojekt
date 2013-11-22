
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
    private final Font buttonFont = new Font("Arial", Font.BOLD, 24);

    private final int RESUME_BUTTON_X = 397;
    private final int RESUME_BUTTON_Y = 160;
    
    private final int EXIT_BUTTON_X = 397;
    private final int EXIT_BUTTON_Y = 240;
    
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
        g.fillRect(0, 50, 1000, 280);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));// Reset
        
        // Buttons
        g.drawImage(green_button, RESUME_BUTTON_X, RESUME_BUTTON_Y, null);
        g.drawImage(red_button, EXIT_BUTTON_X, EXIT_BUTTON_Y, null);
        
        // Button text
        final FontMetrics metrics = g.getFontMetrics(font);
        final FontMetrics buttonMetrics = g.getFontMetrics(buttonFont);
        final String gameOver = "GAME OVER";
        final String resume = "RESUME";
        
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(gameOver, (1000 / 2) - (metrics.stringWidth(gameOver) / 2), 50 + (100 - (metrics.getHeight() / 2)));
        
        g.setFont(buttonFont);
        g.setColor(Color.BLACK);
        g.drawString(resume, RESUME_BUTTON_X + (green_button.getWidth() / 2) - (buttonMetrics.stringWidth(resume) / 2),
                             RESUME_BUTTON_Y + (green_button.getHeight() / 2) + (buttonMetrics.getHeight() / 4));
    }
}
