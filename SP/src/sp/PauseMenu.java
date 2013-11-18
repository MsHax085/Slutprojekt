
package sp;

/**
 *
 * @author Richard Dahlgren
 * @since 2013-okt-18
 * @version 1.0
 */
public class PauseMenu {

    private final int RESUME_BUTTON_X = 0;
    private final int RESUME_BUTTON_Y = 0;
    
    private final int EXIT_BUTTON_X = 0;
    private final int EXIT_BUTTON_Y = 0;
    
    private final int BUTTON_WIDTH = 200;
    private final int BUTTON_HEIGHT = 100;
    
    protected boolean isStartResumeClicked() {
        return false;
    }
    
    protected boolean isExitClicked() {
        return false;
    }
    
    protected void draw() {
        
    }
}
