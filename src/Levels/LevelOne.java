/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package Levels;

import GameObjects.Coordinates;
import java.awt.Graphics;

/**
 *
 * @author j.gore
 */
public class LevelOne extends Level{
    
    public final int BACKGROUND_WIDTH = 4800;
    public final int BACKGROUND_HEIGHT = 600;
    private final String FILE_BACKGROUND = "/Media/backgroundTripleLength.jpg";

    
    public LevelOne(){
        
        super(0,0);
        super.coordinates = new Coordinates(0, 0, BACKGROUND_HEIGHT, BACKGROUND_WIDTH);
        // calls set image method in super class
        setImage(FILE_BACKGROUND);
    }
    @Override
    public void paintComponent(Graphics g){
        /**
         * paints objects onto panel
         * 
         * Params: None
         * 
         * 
         * Return Type: Void
         * 
         */  
        Graphics gCopy = g.create();
        gCopy.drawImage(getImage(), getX(), getY(), BACKGROUND_WIDTH, BACKGROUND_HEIGHT, null);
        
    }
}
