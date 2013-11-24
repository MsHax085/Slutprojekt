package sp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
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
    
    private final int width;
    private final int height;
    private String STATUS = "SP";
    private int score = 0;
    
    protected GamePanel(final EntryPoint entryPoint, final EventListener eventListener, final int width, final int height) {
        
        this.width = width;
        this.height = height;
        this.entryPoint = entryPoint;
        this.eventListener = eventListener;
        this.background = new Background(width);
        this.gameUI = new GameUI();
        this.character = new Character(eventListener);
        this.obstaclePlane = new ObstaclePlane();
        this.pauseMenu = new PauseMenu();
        
        this.gameThread = new GameThread(this);
    }
    
    protected void start() {
        obstaclePlane.update();// Create plane
        this.gameThread.startThread();
    }
    
    protected void update(final double fps, final double ups) {
        //System.out.println("FPS: " + fps + ", UPS: " + ups);
        
        if (STATUS.equals("RUNNING")) {
            
            score += ups / 100;
            background.update();
            obstaclePlane.update();
            character.update();
        }
        
        if (obstaclePlane.isCollidingObstacle(
            character.getX(),
            character.getY(),
            character.getWidth(),
            character.getHeight())) {
            
            STATUS = "SCORE: " + score;
        }
        
        if (eventListener.isEscPressed()) {
            STATUS = "PAUSE";
        }
        
        
        final Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        final Point windowLocation = entryPoint.getLocation();
        final double mouseXInWindow = mouseLocation.getX() - windowLocation.getX();
        final double mouseYInWindow = mouseLocation.getY() - windowLocation.getY();

        final int clickedX = eventListener.getClickedX();
        final int clickedY = eventListener.getClickedY();

        if (STATUS.equals("RUNNING")) {
            if (gameUI.isPauseButton(mouseXInWindow, mouseYInWindow)) {
                entryPoint.setCursor(true);
            } else {
                entryPoint.setCursor(false);
            }
            
            if (gameUI.isPauseButton(clickedX, clickedY)) {
                STATUS = "PAUSE";
            }
        } else {
            
            final boolean exitButton = pauseMenu.isExitButton(mouseXInWindow, mouseYInWindow, true);
            final boolean resumeButton = pauseMenu.isStartResumeButton(mouseXInWindow, mouseYInWindow, true);

            if (!exitButton && !resumeButton) {
                entryPoint.setCursor(false);
                
            } else {

                entryPoint.setCursor(true);
                if (pauseMenu.isExitButton(clickedX, clickedY, false)) {
                    gameThread.stopThread();
                    entryPoint.closeWindow();
                }

                if (pauseMenu.isStartResumeButton(clickedX, clickedY, false)) {
                    
                    if (STATUS.equals("SP") || STATUS.contains("SCORE")) {
                        score = 0;
                        STATUS = "RUNNING";
                        obstaclePlane.reset();
                        
                    } else if (STATUS.equals("PAUSE")) {
                        STATUS = "RUNNING";
                    }
                    entryPoint.setCursor(false);
                }
            }
        }
        eventListener.resetMouse();
    }
    
    protected void draw() {
        
        final BufferStrategy bf = entryPoint.getBufferStrategy();
        Graphics g = null;
        
        try {
            g = bf.getDrawGraphics();
            
            // Reset
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, width, height);
            
            // Draw to buffer - grayscale
            if (STATUS.equals("RUNNING")) {
                background.draw(g, false);
                obstaclePlane.draw(g, false);
                character.draw(g);
                gameUI.draw(g, score);
            } else {
                background.draw(g, true);
                obstaclePlane.draw(g, true);
                character.draw(g);
                gameUI.draw(g, score);
                pauseMenu.draw(g, STATUS);
            }
            
        } finally {
            if (g != null) {
                g.dispose();
            }
        }
        
        // Show buffer
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }
}
