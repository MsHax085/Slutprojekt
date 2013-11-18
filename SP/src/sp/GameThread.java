
package sp;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Richard Dahlgren
 * @since 2013-okt-18
 * @version 1.0
 */
public class GameThread implements Runnable {
    
    private final GamePanel gamePanel;
    private final Thread thread;
    
    private final int MAX_FPS = 120;
    private final int MAX_FRAME_SKIPS = 5;
    private final int FRAME_PERIOD = 1000 / MAX_FPS;
    
    private boolean run = false;
    private final LinkedList<Integer> loggedFPS = new LinkedList<>();
    private final LinkedList<Integer> loggedUPS = new LinkedList<>();
    private long statisticsPoll = 0;
    private int ups = 0;
    private int fps = 0;
    
    protected GameThread(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.thread = new Thread(this);
    }
    
    @Override
    public void run() {
        
        long startTime;
        long deltaTime;
        int sleepTime;
        int framesSkipped;
        
        run = true;
        statisticsPoll = System.currentTimeMillis();
        
        while (run) {
            
            startTime = System.currentTimeMillis();
            framesSkipped = 0;
            
            updateStatistics(true);
            gamePanel.update(getAverageFromList(loggedFPS), getAverageFromList(loggedUPS));// Expensive for each frame!!!
            gamePanel.draw();
            
            deltaTime = System.currentTimeMillis() - startTime;
            sleepTime = (int)(FRAME_PERIOD - deltaTime);
            
            if (sleepTime > 0) {
                try {
                    
                    Thread.sleep(sleepTime);
                    
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            while (sleepTime < 0 && framesSkipped < MAX_FRAME_SKIPS) {
                
                updateStatistics(false);
                gamePanel.update(getAverageFromList(loggedFPS), getAverageFromList(loggedUPS));// Expensive for each frame!!!
                sleepTime += FRAME_PERIOD;
                framesSkipped++;
            }
        }
    }
    
    private void updateStatistics(final boolean updateFPS) {
        
        if (System.currentTimeMillis() - statisticsPoll >= 100) {// Every 1/10 second
            
            loggedFPS.add(fps * 10);
            loggedUPS.add(ups * 10);
            
            fps = 0;
            ups = 0;
            
            if (loggedFPS.size() > 10) {
                loggedFPS.poll();
            }
            
            if (loggedUPS.size() > 10) {
                loggedUPS.poll();
            }
            
            statisticsPoll = System.currentTimeMillis();
        }
        
        if (updateFPS) {
            fps++;
        }
        ups++;
    }
    
    private double getAverageFromList(final LinkedList<Integer> list) {
        
        if (list.size() < 1) {
            return 0;
        }
        
        double sum = 0;
        for (Integer value : list) {
            sum += value;
        }
        return sum / (double) 10;// 10 = size of list
    }
    
    protected void startThread() {
        run = true;
        thread.start();
    }
    
    protected void stopThread() {
        run = false;
    }
}
