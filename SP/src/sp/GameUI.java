
package sp;

import java.awt.Font;
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
    private final JLabel score;
    
    private final int pauseX = 0;
    private final int pauseY = 0;
    
    private final int scoreX = 0;
    private final int scoreY = 0;
    
    protected GameUI() {
        
        try {
            
            final File pauseFile = new File("src/sp/resources/pause.png");
            pauseIcon = ImageIO.read(pauseFile);
            
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        font = new Font("Verdana", Font.BOLD, 34);
        score = new JLabel("0");
        score.setFont(font);
    }
    
    protected boolean isPauseClicked(final int mouseX, final int mouseY) {
        return false;
    }
    
    protected void draw() {
        
    }
}
