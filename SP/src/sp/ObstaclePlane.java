
package sp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
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

    private BufferedImage crateImage;
    private final int y = 300;
    private final int WIDTH = 600;
    private final int HEIGHT = 128;
    private final int SPEED = 2;
    private final int MAX_OBSTACLES = 12;
    private final int OBSTACLE_MIN_START_X = 400;
    
    private final ArrayList<Point> obstacles = new ArrayList<>();
    private double lastObstacleX = 0;
    private final Random rand = new Random();
    
    protected ObstaclePlane() {
        
        try {
            
            final File obstacleImageFile = new File("src/sp/resources/crate.jpg");
            crateImage = ImageIO.read(obstacleImageFile);
            
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void update() {
        
        int crateWidth = crateImage.getWidth() / 6;
        int largestX = (int) (Math.ceil((double) WIDTH / (double)crateWidth) + 1) * crateWidth;
        
        if (obstacles.isEmpty()) {
            
            // Create horizontal plane
            for (int crate = 0; crate <= largestX; crate += crateWidth) {
                obstacles.add(new Point(crate, y));
            }
            
            // Create obstacles
            
            int newObstacles = 4;//rand.nextInt(MAX_OBSTACLES / 2) + (MAX_OBSTACLES / 2) + 1;// Always 1/2 of max obs
            for (int crate = 0; crate < newObstacles; crate++) {
                
                // Reset
                int xPos = (int)lastObstacleX;
                int yPos = y;
                
                xPos += (lastObstacleX < WIDTH) ? (WIDTH - lastObstacleX) : 0;
                xPos += crateWidth * 3;
                xPos += (rand.nextInt(2) + 2) * crateWidth;
                
                yPos -= 3 * crateWidth;
                yPos += (rand.nextInt(10) < 7) ? (2 * crateWidth) : 0;
                lastObstacleX = xPos;
                
                obstacles.add(new Point(xPos, yPos));
            }
            
            return;
        }
        double l = lastObstacleX;
        lastObstacleX -= SPEED;
        System.out.println("1) " + (l - lastObstacleX));
        for (Point obstacle : obstacles) {
            
            double i = obstacle.getX();
            double newXPos = obstacle.getX() - SPEED;
            double newYPos = obstacle.getY();
            
            System.out.println("2) " + (i - newXPos));
            
            if (newXPos + crateWidth < 100) {
                
                if (newYPos == y) {// Horizontal plane
                    newXPos += largestX;
                    
                } else {// Obstacles
                    newXPos = (lastObstacleX < 600) ? 600 : lastObstacleX;
                    newXPos += (rand.nextInt(3) + 3) * crateWidth;
                    
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
        
        for (Point obstacle : obstacles) {
            /*if (obstacle.getY() != y) {
                g.setColor(Color.GREEN);
                g.drawLine((int)obstacle.getX(), 0, (int)obstacle.getX(), 400);
            }*/
            g.drawImage(crateImage, (int)obstacle.getX(), (int)obstacle.getY(), crateImage.getWidth() / 6, crateImage.getHeight() / 6, null);
        }
        
        g.setColor(Color.RED);
        g.drawLine(100, 0, 100, 400);
        g.drawLine(600, 0, 600, 400);
        g.setColor(Color.YELLOW);
        g.drawString("Var", (int)lastObstacleX + 8, 95);
        g.fillOval((int) lastObstacleX - 2, 100, 4, 4);
    }
    
    protected boolean isCollidingObstacle() {
        return false;
    }
    
    protected int getY() {
        return y;
    }
}
