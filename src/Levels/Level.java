/**
 * FinalProject2014.java - My solution for the I.B. Internal Assessment
 * @since 4-Dec-2014
 * @instructor Mr. Wachs
 * @author Jack Gore j.gore
 */

package Levels;

import GameObjects.Coordinates;
import Interfaces.GamePanel;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author j.gore
 */
public class Level extends JPanel{
    
    private int x;
    private int y;
    private Image backgroundImg;
    public Coordinates coordinates;
    
    public Level(int x, int y){

        this.x = x;
        this.y = y;
    }
    public void setImage(String filename){
        /**
         * Sets the background image
         * 
         * Params: 
         * 
         * filename is the file name of the image being used
         * 
         * Return Type: Void
         * 
         */  
        ImageIcon ii = new ImageIcon(this.getClass().getResource(filename));
        this.backgroundImg = ii.getImage();
    }
    public Image getImage(){
        // returns image 
        return this.backgroundImg;
    }
    public void destroy(){
        // Sets image to null
        backgroundImg = null;
    }
    @Override
    public int getX(){
        // returns x of the image
        return this.x;
    }
    @Override
    public int getY(){
        //returns y of the image
        return this.y;
    }    
}
