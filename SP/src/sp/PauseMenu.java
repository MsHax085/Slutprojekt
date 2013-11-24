
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
    
    private BufferedImage resume_button;
    private BufferedImage exit_button;
    private BufferedImage resume_button_hover;
    private BufferedImage exit_button_hover;
    private final Font font = new Font("Arial", Font.BOLD, 48);
    private final Font buttonFont = new Font("Arial", Font.BOLD, 24);

    private final int RESUME_BUTTON_X = 397;
    private final int RESUME_BUTTON_Y = 160;
    
    private final int EXIT_BUTTON_X = 397;
    private final int EXIT_BUTTON_Y = 240;
    
    private boolean RESUME_BUTTON_HOVER = false;
    private boolean EXIT_BUTTON_HOVER = false;
    
    protected PauseMenu() {
        
        try {
            final File green_button_file = new File("src/sp/resources/green_button.png");
            final File red_button_file = new File("src/sp/resources/red_button.png");
            final File green_button_hover_file = new File("src/sp/resources/green_button_hover.png");
            final File red_button_hover_file = new File("src/sp/resources/red_button_hover.png");
            
            resume_button = ImageIO.read(green_button_file);
            exit_button = ImageIO.read(red_button_file);
            resume_button_hover = ImageIO.read(green_button_hover_file);
            exit_button_hover = ImageIO.read(red_button_hover_file);
        
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected boolean isStartResumeButton(final double x, final double y) {
        
        if (x >= RESUME_BUTTON_X && x <= RESUME_BUTTON_X + resume_button.getWidth()) {
            if (y >= RESUME_BUTTON_Y && y <= RESUME_BUTTON_Y + resume_button.getHeight()) {
                RESUME_BUTTON_HOVER = true;
                return true;
            }
        }
        
        RESUME_BUTTON_HOVER = false;
        return false;
    }
    
    protected boolean isExitButton(final double x, final double y) {
        
        if (x >= EXIT_BUTTON_X && x <= EXIT_BUTTON_X + exit_button.getWidth()) {
            if (y >= EXIT_BUTTON_Y && y <= EXIT_BUTTON_Y + exit_button.getHeight()) {
                EXIT_BUTTON_HOVER = true;
                return true;
            }
        }
        
        EXIT_BUTTON_HOVER = false;
        return false;
    }
    
    protected void draw(final Graphics graphics, final String STATUS) {
        
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
        if (RESUME_BUTTON_HOVER) {
            g.drawImage(resume_button_hover, RESUME_BUTTON_X, RESUME_BUTTON_Y, null);
        } else {
            g.drawImage(resume_button, RESUME_BUTTON_X, RESUME_BUTTON_Y, null);
        }
        
        if (EXIT_BUTTON_HOVER) {
            g.drawImage(exit_button_hover, EXIT_BUTTON_X, EXIT_BUTTON_Y, null);
        } else {
            g.drawImage(exit_button, EXIT_BUTTON_X, EXIT_BUTTON_Y, null);
        }
        
        // Button text
        final FontMetrics metrics = g.getFontMetrics(font);
        final FontMetrics buttonMetrics = g.getFontMetrics(buttonFont);
        final String exit = "Exit";
        String resume = "RESUME";
        
        if (STATUS.equals("GAME OVER") || STATUS.equals("SP")) {
            resume = "START";
        }
        
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(STATUS, (1000 / 2) - (metrics.stringWidth(STATUS) / 2), 50 + (100 - (metrics.getHeight() / 2)));
        
        g.setFont(buttonFont);
        g.setColor(Color.BLACK);
        g.drawString(resume, RESUME_BUTTON_X + (resume_button.getWidth() / 2) - (buttonMetrics.stringWidth(resume) / 2),
                             RESUME_BUTTON_Y + (resume_button.getHeight() / 2) + (buttonMetrics.getHeight() / 2) - 3);
        g.drawString(exit, EXIT_BUTTON_X + (exit_button.getWidth() / 2) - (buttonMetrics.stringWidth(exit) / 2),
                           EXIT_BUTTON_Y + (exit_button.getHeight() / 2) + (buttonMetrics.getHeight() / 2) - 3);
    }
}
