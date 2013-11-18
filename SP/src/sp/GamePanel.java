package sp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Richard Dahlgren
 * @since 2013-okt-18
 * @version 1.0
 */
public class GamePanel {

    private final EntryPoint entryPoint;
    private final EventListener eventListener;
    private final Background background;
    private final GameUI gameUI;
    private final Character character;
    private final ObstaclePlane obstaclePlane;
    private final PauseMenu pauseMenu;
    private final GameThread gameThread;
    
    private boolean EXIT_REQUESTED = false;
    private boolean PAUSE_REQUESTED = true;// Start in pause mode
    
    private int score = 0;
    private final int width;
    private final int height;
    
    protected GamePanel(final EntryPoint entryPoint, final int width, final int height) {
        
        this.width = width;
        this.height = height;
        this.entryPoint = entryPoint;
        this.eventListener = new EventListener(this);
        this.background = new Background();
        this.gameUI = new GameUI();
        this.character = new Character(this);
        this.obstaclePlane = new ObstaclePlane();
        this.pauseMenu = new PauseMenu();
        
        this.gameThread = new GameThread(this);
    }
    
    protected void start() {
        this.gameThread.startThread();
    }
    protected void update(final double fps, final double ups) {
        //System.out.println("FPS: " + fps + ", UPS: " + ups);
        background.update();
        obstaclePlane.update();
    }
    
    protected void draw() {
        
        final BufferStrategy bf = entryPoint.getBufferStrategy();
        Graphics g = null;
        
        try {
            g = bf.getDrawGraphics();
            
            // Reset
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
            
            // Draw to buffer
            background.draw(g);
            obstaclePlane.draw(g);
            
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
        
        // Show buffer
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }
    
    protected ObstaclePlane getObstactlePlane() {
        return obstaclePlane;
    }
}
