package sp;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author Richard Dahlgren
 * @since 2013-okt-18
 * @version 1.0
 */
public class EntryPoint {
    
    private final JFrame window;
    private final EventListener eventListener;

    public static void main(final String[] args) {
        final EntryPoint entryPoint = new EntryPoint();
    }
    
    protected EntryPoint() {
        
        System.setProperty("sun.java2d.transaccel", "True");
        System.setProperty("sun.java2d.d3d", "True");
        System.setProperty("sun.java2d.ddforcevram", "True");
        
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("SP");
        window.setSize(1000, 359);// def.width = 600
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        
        eventListener = new EventListener();
        final GamePanel gamePanel = new GamePanel(this, eventListener, 1000, 359);// def.width = 600
        
        window.addKeyListener(eventListener);
        window.addMouseListener(eventListener);
        window.requestFocus();
        window.setVisible(true);
        window.createBufferStrategy(2);
        gamePanel.start();
    }
    
    protected BufferStrategy getBufferStrategy() {
        return window.getBufferStrategy();
    }
    
    protected Point getLocation() {
        return window.getLocation();
    }
    
    protected void closeWindow() {
        window.setVisible(false);
        System.exit(0);
    }
    
    protected void setCursor(final boolean hand) {
        if (hand) {
            window.setCursor(new Cursor(Cursor.HAND_CURSOR));
            return;
        }
        window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
