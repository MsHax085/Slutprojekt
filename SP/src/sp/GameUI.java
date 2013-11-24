
package sp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JLabel;

/**
 *
 * @author Richard Dahlgren
 * @since 2013-okt-18
 * @version 1.0
 */
public class GameUI {

    private BufferedImage pauseIcon;
    private final Font font;
    private final Color textColor = new Color(51, 51, 51);
    
    private final int PAUSE_X = 926;
    private final int PAUSE_Y = 40;
    
    private final int SCORE_X = 15;
    private final int SCORE_Y = 50;
    
    protected GameUI() {
        
        try {
            
            final File pauseFile = new File("src/sp/resources/pause.png");
            pauseIcon = ImageIO.read(pauseFile);
            
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        font = new Font("Verdana", Font.BOLD, 18);
    }
    
    protected boolean isPauseButton(final double x, final double y) {
        
        if (x >= PAUSE_X && x <= PAUSE_X + pauseIcon.getWidth()) {
            if (y >= PAUSE_Y && y <= PAUSE_Y + pauseIcon.getHeight()) {
                return true;
            }
        }
        
        return false;
    }
    
    protected void draw(final Graphics graphics, final int score) {
        
        final Graphics2D g = (Graphics2D) graphics;
        // Smoother text
        RenderingHints rh = new RenderingHints(
                            RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_GASP);
        g.setRenderingHints(rh);
        
        g.drawImage(pauseIcon, PAUSE_X, PAUSE_Y, null);
        
        g.setFont(font);
        g.setColor(textColor);
        g.drawString("SCORE: " + score, SCORE_X, SCORE_Y);
    }
}
