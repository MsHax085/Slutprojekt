
package sp;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Richard Dahlgren
 * @since 2013-okt-18
 * @version 1.0
 */
public class ObstaclePlane {

    private BufferedImage planeImage;
    private BufferedImage obstacleImage;
    private final int y = 290;
    private final int WIDTH = 1000;// def.width = 600
    private final float SPEED = 1.85f;
    private final int MAX_OBSTACLES = 6;
    
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();
    private float lastObstacleX = 0;
    private final Random rand = new Random();
    
    protected ObstaclePlane() {
        
        try {
            
            final File planeImageFile = new File("src/sp/resources/plane.png");
            final File obstacleImageFile = new File("src/sp/resources/crate.png");
            
            planeImage = ImageIO.read(planeImageFile);
            obstacleImage = ImageIO.read(obstacleImageFile);
            
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void update() {
        
        final int crateWidth = planeImage.getWidth();
        final double lastPlaneObstacleX = (Math.ceil((double) WIDTH / (double)crateWidth) + 1) * crateWidth;
        
        if (obstacles.isEmpty()) {
            
            // Create horizontal plane
            for (int crate = 0; crate <= lastPlaneObstacleX; crate += crateWidth) {
                obstacles.add(new Obstacle(crate, y));
            }
            
            // Create obstacles
            final int halfMaxObstacles = MAX_OBSTACLES / 2;
            final int newObstacles = rand.nextInt(halfMaxObstacles) + (halfMaxObstacles) + 1;// 1/2 of max obstacles to max obstacles
            
            for (int crate = 0; crate < newObstacles; crate++) {
                
                // Reset
                float xPos = lastObstacleX;
                float yPos = y;
                
                xPos += (lastObstacleX < WIDTH) ? (WIDTH - lastObstacleX) : 0;
                xPos += crateWidth * 3;
                xPos += (rand.nextInt(2) + 2) * crateWidth;
                
                yPos -= 3 * crateWidth;
                yPos += (rand.nextInt(10) < 7) ? (2 * crateWidth) : 0;
                
                lastObstacleX = xPos;
                obstacles.add(new Obstacle(xPos, yPos));
            }
            
            return;
        }
        
        lastObstacleX -= SPEED;
        for (Obstacle obstacle : obstacles) {
            
            // Update all obstacle positions
            float newXPos = obstacle.getX() - SPEED;
            float newYPos = obstacle.getY();
            
            if (newXPos + crateWidth < 0) {// Outside screen
                
                if (newYPos == y) {// Horizontal plane
                    newXPos += lastPlaneObstacleX;
                    
                } else {// Obstacles
                    newXPos = (lastObstacleX < WIDTH) ? WIDTH : lastObstacleX;
                    newXPos += crateWidth * 3;
                    newXPos += (rand.nextInt(2) + 2) * crateWidth;
                    
                    newYPos = y;
                    newYPos -= 3 * crateWidth;
                    newYPos += (rand.nextInt(10) < 8) ? (2 * crateWidth) : 0;
                    
                    lastObstacleX = newXPos;
                }
            }
            obstacle.setLocation(newXPos, newYPos);
        }
    }
    
    protected void draw(final Graphics g) {
        
        final int crateSize = planeImage.getWidth();// Height == Width
        
        for (Obstacle obstacle : obstacles) {
            
            final int obstacleX = obstacle.getX_int();
            final int obstacleY = obstacle.getY_int();
            
            if (obstacleY == y) {
                g.drawImage(planeImage, obstacleX, obstacleY, crateSize, crateSize, null);
            } else {
                g.drawImage(obstacleImage, obstacleX, obstacleY, crateSize, crateSize, null);
            }
        }
    }
    
    protected boolean isCollidingObstacle() {
        return false;
    }
    
    protected int getY() {
        return y;
    }
}
