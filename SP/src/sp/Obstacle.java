
package sp;

/**
 *
 * @author Richard
 * @since 2013-nov-18
 * @version 1.0
 */
public class Obstacle {

    private float x;
    private float y;
    
    protected Obstacle(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    protected Obstacle(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    // -------------------------------------------------------------------------
    
    protected float getX() {
        return x;
    }
    
    protected int getX_int() {
        return (int) x;
    }
    
    protected float getY() {
        return y;
    }
    
    protected int getY_int() {
        return (int) y;
    }
    
    protected void setLocation(float x, float y) {
        this.x = x;
        this.y = y;
    }
}
