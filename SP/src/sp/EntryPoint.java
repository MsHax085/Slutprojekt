package sp;

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
        window.setSize(600, 359);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        
        final GamePanel gamePanel = new GamePanel(this, 600, 359);
        
        window.setVisible(true);
        window.createBufferStrategy(2);
        gamePanel.start();
    }
    
    protected BufferStrategy getBufferStrategy() {
        return window.getBufferStrategy();
    }
    
    protected void closeWindow() {
        window.setVisible(false);
        System.exit(0);
    }
}
