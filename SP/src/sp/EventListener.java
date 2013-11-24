
package sp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author Richard Dahlgren
 * @since 2013-okt-18
 * @version 1.0
 */
public class EventListener implements MouseListener, KeyListener {
    
    private boolean SPACE_PRESSED = false;
    private boolean ESC_PRESSED = false;
    private int clickedX;
    private int clickedY;
    
    // Event functions ---------------------------------------------------------

    @Override
    public void mousePressed(MouseEvent event) {
        clickedX = event.getX();
        clickedY = event.getY();
    }
    
    @Override
    public void keyPressed(KeyEvent event) {
        
        final int keyCode = event.getKeyCode();
        
        if (keyCode == KeyEvent.VK_SPACE) {
            SPACE_PRESSED = true;
        }
        
        if (keyCode == KeyEvent.VK_ESCAPE) {
            ESC_PRESSED = true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent event) {
        
        final int keyCode = event.getKeyCode();
        
        if (keyCode == KeyEvent.VK_SPACE) {
            SPACE_PRESSED = false;
        }
        
        if (keyCode == KeyEvent.VK_ESCAPE) {
            ESC_PRESSED = false;
        }
    }
    
    // Getters & setters -------------------------------------------------------
    
    protected void resetMouse() {
        clickedX = 0;
        clickedY = 0;
    }
    
    protected boolean isSpacePressed() {
        return SPACE_PRESSED;
    }
    
    protected boolean isEscPressed() {
        return ESC_PRESSED;
    }
    
    protected int getClickedX() {
        return clickedX;
    }
    
    protected int getClickedY() {
        return clickedY;
    }
    
    // Un-used functions -------------------------------------------------------
    
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
